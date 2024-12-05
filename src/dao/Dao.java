package dao;

import java.util.List;
import java.util.Optional;

// K - тип id, T -  сам тип entity
public interface Dao<K, T> {

    List<T> findAll();

    Optional<T> findById(K id);

    // удаляем
    boolean delete(K id);

    void update(T entity);

    // возвращаем сохраненную сущность
    T save(T entity);

}
