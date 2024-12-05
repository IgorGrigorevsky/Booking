package service;

import dao.DaoRoomClass;
import dto.roomClass.RoomClassDto;
import dto.roomClass.RoomClassFilter;
import entity.RoomClass;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoomClassService {

    // паттерн Singleton
    private static final RoomClassService INSTANCE = new RoomClassService();

    private final DaoRoomClass daoRoomClass = DaoRoomClass.getInstance();

    private RoomClassService() {
    }

    public static RoomClassService getINSTANCE() {
        return INSTANCE;
    }

    public RoomClassDto save(RoomClass roomClass) {
        RoomClass savedRoomClass = daoRoomClass.save(roomClass);
        return new RoomClassDto(savedRoomClass.getId(), savedRoomClass.getRoomClass());
    }

    public List<RoomClassDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты RoomClass в RoomClassDto
        return daoRoomClass.findAll().stream()
                .map(roomClass -> new RoomClassDto(
                        roomClass.getId(), roomClass.getRoomClass()
                )).collect(toList());
    }

    public List<RoomClassDto> findById(Long id) {
        return daoRoomClass.findById(id).stream()
                .map(roomClass -> new RoomClassDto(
                        roomClass.getId(), roomClass.getRoomClass()
                )).collect(toList());
    }

    public List<RoomClassDto> findAllWithFilters(RoomClassFilter filter) {
        return daoRoomClass.findAllWithFilters(filter).stream()
                .map(roomClass -> new RoomClassDto(roomClass.getId(), roomClass.getRoomClass()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoRoomClass.delete(id);
    }
}
