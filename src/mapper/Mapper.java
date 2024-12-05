package mapper;

// интерфейс для преобразования из одного типа From в тип To
public interface Mapper<F, T> {

    // метод для преобразования типов
    T mapFrom(F object);
}
