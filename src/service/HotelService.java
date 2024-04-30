package service;

import dao.DaoHotel;
import dto.hotel.CreateHotelDto;
import dto.hotel.HotelDto;
import dto.hotel.HotelFilter;
import entity.Hotel;
import exception.ValidationException;
import mapper.CreateHotelMapper;
import validator.CreateHotelValidator;
import validator.ValidationResult;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class HotelService {

    // паттерн Singleton
    private static final HotelService INSTANCE = new HotelService();

    private final CreateHotelValidator createHotelValidator = CreateHotelValidator.getINSTANCE();
    private final DaoHotel daoHotel = DaoHotel.getInstance();
    private final CreateHotelMapper createHotelMapper = CreateHotelMapper.getINSTANCE();

    private HotelService() {
    }

    public static HotelService getINSTANCE() {
        return INSTANCE;
    }

    public Long create (CreateHotelDto createHotelDto){

        // 1 шаг: validation
        // Чтобы понять - правильные ли данные ввели - мы можем это сделать на уровне сервисов,
        // но и на уровне сервлетов, что неудобно.
        // Используют сервисы - т.к. можно пойти в БД - проверить какие-то данные на уникальность и т.д.
        // на уровне клиента - используется клиентская валидация, но она не обязательна.
        // В случае backend'а - серверная валидация - она должна быть обязательна.
        ValidationResult validResult = createHotelValidator.isValid(createHotelDto);
        if (!validResult.isValid()){
            throw new ValidationException(validResult.getErrors());
        }

        // 2 шаг: map
        // После того, как мы сделали валидацию, нам необходимо преобразовать нашу Dto в сущность (Entity),
        // т.к. мы работаем на уровне Dao с сущностями

        Hotel hotel = createHotelMapper.mapFrom(createHotelDto);

        // 3 шаг: hotelDao.save()
        // после того, как мы преобразовали Dto в Entity, мы можем воспользоваться методом .save()
        // HotelDao.save() -> для того, чтобы сохранить преобразованную сущность из предыдущего шага
        Hotel savedHotel = daoHotel.save(hotel);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedHotel.getId();
    }

    public HotelDto save(Hotel hotel) {
        Hotel savedHotel = daoHotel.save(hotel);
        return new HotelDto(savedHotel.getId(), savedHotel.getName(), savedHotel.getAddress(),
                savedHotel.getPhone(), savedHotel.getEmail(), savedHotel.getStarsId());
    }

    public List<HotelDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты Hotel в HotelDto
        return daoHotel.findAll().stream()
                .map(hotel -> new HotelDto(hotel.getId(), hotel.getName(), hotel.getAddress(),
                        hotel.getPhone(), hotel.getEmail(), hotel.getStarsId()
                )).collect(toList());
    }

    public List<HotelDto> findById(Long id) {
        return daoHotel.findById(id).stream()
                .map(hotel -> new HotelDto(hotel.getId(), hotel.getName(), hotel.getAddress(),
                        hotel.getPhone(), hotel.getEmail(), hotel.getStarsId()
                )).collect(toList());
    }

    public List<HotelDto> findAllWithFilters(HotelFilter filter) {
        return daoHotel.findAllWithFilters(filter).stream()
                .map(hotel -> new HotelDto(hotel.getId(), hotel.getName(), hotel.getAddress(),
                        hotel.getPhone(), hotel.getEmail(), hotel.getStarsId()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoHotel.delete(id);
    }
}