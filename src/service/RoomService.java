package service;

import dao.DaoRoom;
import dto.room.RoomDto;
import dto.room.RoomFilter;
import entity.Room;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoomService {

    // паттерн Singleton
    private static final RoomService INSTANCE = new RoomService();

    private final DaoRoom daoRoom = DaoRoom.getInstance();

    private RoomService() {
    }

    public static RoomService getINSTANCE() {
        return INSTANCE;
    }


    public RoomDto save(Room room) {
        Room savedRoom = daoRoom.save(room);
        return new RoomDto(savedRoom.getId(), savedRoom.getHotelId(), savedRoom.getBedsCount(),
                savedRoom.getFloor(), savedRoom.getIncludedBreakfast(), savedRoom.getClassId(),
                savedRoom.getPrice());
    }


    public List<RoomDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты Room в RoomDto
        return daoRoom.findAll().stream()
                .map(room -> new RoomDto(
                        room.getId(), room.getHotelId(), room.getBedsCount(), room.getFloor(),
                        room.getIncludedBreakfast(), room.getClassId(), room.getPrice()
                )).collect(toList());
    }

    public List<RoomDto> findById(Long id) {
        return daoRoom.findById(id).stream()
                .map(room -> new RoomDto(
                        room.getId(), room.getHotelId(), room.getBedsCount(), room.getFloor(),
                        room.getIncludedBreakfast(), room.getClassId(), room.getPrice()
                )).collect(toList());
    }

    public List<RoomDto> findAllWithFilters(RoomFilter filter) {
        return daoRoom.findAllWithFilters(filter).stream().map(room -> new RoomDto(room.getId(), room.getHotelId(), room.getBedsCount(), room.getFloor(),
                room.getIncludedBreakfast(), room.getClassId(), room.getPrice()
        )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoRoom.delete(id);
    }
}

