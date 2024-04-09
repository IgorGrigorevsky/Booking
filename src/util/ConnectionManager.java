package util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {


    // создаем константы для properties-файла
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final Integer DEFAULT_POOL_SIZE = 10;

    // потокобезопасная коллекция для работы сервера
    private static BlockingQueue<Connection> pool;
    // коллекция для закрытия соединения
    private static List<Connection> sourcesConnectionList;


    // т.к. класс является утилитным
    private ConnectionManager() {
    }

    static {
        // явно передаем драйвер для использования
        loadDriver();

        // создаем метод инициализации пула соединений
        initConnectionPool();
    }

    private static void initConnectionPool() {
        String poolSize = PropertiesUtil.get(POOL_SIZE_KEY);

        // на случай default'ного значения pool-size
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourcesConnectionList = new ArrayList<>(size);
        // инициализируем connection'ы
        for (int i = 0; i < size; i++) {
            // вместо этого -> pool.add(open());
            Connection connection = open();
            //не передаем соединение сразу в pool, используем Reflecton-API (т.е. Proxy)
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                    // если вызвался метод "close", то добавляем соединение в pool
                    // иначе продолжаем выполнение нашего метода
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(proxyConnection);

            // т.к. у нас лежат Proxy-объекты, то не можем вызвать метод .close() у Proxy
            // (он возвращает соединение в пул, а не закрывает его)
            // Для закрытия пула соединений используем коллекцию, где хранится исходные соединения,
            sourcesConnectionList.add(connection);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // получение соединения из пула
    public static Connection get() {
        // метод .take() - возвращает соединение, если оно есть
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // закрываем метод, чтобы никто случайно не открыл соединение
    private static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closePool() {
        for (Connection sourceConnection : sourcesConnectionList) {
            try {
                sourceConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


