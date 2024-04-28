package service;

import dao.DaoHotelStars;
import dto.hotelStars.HotelStarsDto;
import dto.hotelStars.HotelStarsFilter;
import entity.HotelStars;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class HotelStarsService {

    // паттерн Singleton
    private static final HotelStarsService INSTANCE = new HotelStarsService();

    private final DaoHotelStars daoHotelStars = DaoHotelStars.getInstance();

    private HotelStarsService() {
    }

    public static HotelStarsService getINSTANCE() {
        return INSTANCE;
    }

    public HotelStarsDto save(HotelStars hotelStars) {
        HotelStars savedHotelStars = daoHotelStars.save(hotelStars);
        return new HotelStarsDto(savedHotelStars.getId(), savedHotelStars.getStars());
    }

    public List<HotelStarsDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты HotelStars в HotelStarsDto
        return daoHotelStars.findAll().stream()
                .map(hotelStars -> new HotelStarsDto(hotelStars.getId(), hotelStars.getStars()
                )).collect(toList());
    }

    public List<HotelStarsDto> findById(Long id) {
        return daoHotelStars.findById(id).stream()
                .map(hotelStars -> new HotelStarsDto(hotelStars.getId(), hotelStars.getStars()
                )).collect(toList());
    }

    public List<HotelStarsDto> findAllWithFilters(HotelStarsFilter filter) {
        return daoHotelStars.findAllWithFilters(filter).stream()
                .map(hotelStars -> new HotelStarsDto(hotelStars.getId(), hotelStars.getStars()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoHotelStars.delete(id);
    }
}