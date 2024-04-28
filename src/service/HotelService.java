package service;

import dao.DaoHotel;
import dto.hotel.HotelDto;
import dto.hotel.HotelFilter;
import entity.Hotel;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class HotelService {

    // паттерн Singleton
    private static final HotelService INSTANCE = new HotelService();

    private final DaoHotel daoHotel = DaoHotel.getInstance();

    private HotelService() {
    }

    public static HotelService getINSTANCE() {
        return INSTANCE;
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