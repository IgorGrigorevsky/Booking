package runner;

import dao.DaoRoomClass;
import dto.roomClass.RoomClassFilter;
import entity.RoomClass;

import java.util.List;
import java.util.Optional;

public class DaoRoomClassRunner {
    public static void main(String[] args) {

        // todo пустой optional
        // CREATE
        saveRoomClass("среднячок");

        // READ ALL
        selectAll();
        selectAllWithFilter(5, 0, 8L, "ред");

        // UPDATE
        updateRoomClass(10L, "супер-пупер");

        // DELETE
        deleteRoomClass(10L);
    }

    private static void saveRoomClass(String nameClass) {
        DaoRoomClass daoRoomClass = DaoRoomClass.getInstance();
        RoomClass roomClass = new RoomClass();

        roomClass.setRoomClass(nameClass);

        RoomClass savedValue = daoRoomClass.save(roomClass);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<RoomClass> all = DaoRoomClass.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(int limit, int offset, Long id, String roomClass) {
        RoomClassFilter filter = new RoomClassFilter(limit, offset, id, roomClass);
        List<RoomClass> allWithFilters = DaoRoomClass.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateRoomClass(Long id, String nameRoomClass) {
        DaoRoomClass instance = DaoRoomClass.getInstance();
        Optional<RoomClass> maybeEntity = instance.findById(id);
        System.out.println("имеющийся class: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(roomClass -> {
            roomClass.setRoomClass(nameRoomClass);
            instance.update(roomClass);
        });
        System.out.println("измененный class: \n" + maybeEntity);
    }

    private static void deleteRoomClass(long id) {
        DaoRoomClass daoRoomClass = DaoRoomClass.getInstance();
        boolean result = daoRoomClass.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}