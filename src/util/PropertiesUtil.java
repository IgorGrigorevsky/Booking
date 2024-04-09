package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    // для работы с нашим properties-файлом получаем объект-константу класса Properties
    private static final Properties PROPERTIES = new Properties();

    // используем статический блок инициализации для загрузки properties-файла вначале работы приложения
    static {
        loadProperties();
    }

    // метод получения value по ключу
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    // т.к. класс является утилитным
    private PropertiesUtil() {
    }

    // метод для считывания properties-файла из нашего classPath'а
    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            // если не смогли прочитать наш application.properties файл, пробрасываем исключение,
            // чтобы наше приложение упало
            throw new RuntimeException(e);
        }
    }


}
