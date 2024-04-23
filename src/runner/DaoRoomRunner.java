package runner;

import dao.DaoRoom;
import dto.RoomFilter;
import entity.Room;

import java.util.List;
import java.util.Optional;

public class DaoRoomRunner {
    public static void main(String[] args) {

        // todo при update - на консоль выводит optional
        // CREATE
        saveRoom(1L, 3, 5, true, 3L, 15000);

        // READ
        selectAll();
        selectAllWithFilter(5, 0, 1L, null, 2, 4, null, null, 16000);

        // UPDATE
        updateRoomHotelId(28L, 2L);
        updateRoomBedsCount(28L, 5);
        updateRoomFloor(28L, 17);
        updateRoomIncludedBreakfast(28L, true);
        updateRoomClassId(28L, 3L);
        updateRoomPrice(28L, 30000);

        // DELETE
        deleteRoom(28L);
    }

    private static void saveRoom(long hotelId, int bedsCount, int floor, boolean includedBreakfast, long classId, int price) {
        DaoRoom daoRoom = DaoRoom.getInstance();
        Room room = new Room();

        room.setHotelId(hotelId);
        room.setBedsCount(bedsCount);
        room.setFloor(floor);
        room.setIncludedBreakfast(includedBreakfast);
        room.setClassId(classId);
        room.setPrice(price);

        Room savedValue = daoRoom.save(room);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<Room> all = DaoRoom.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(int limit, int offset, Long id, Long hotelId, Integer bedsCount,
                                            int floor, Boolean includedBreakfast, Long classId, Integer price) {
        RoomFilter filter = new RoomFilter(limit, offset, id, hotelId,
                bedsCount, floor, includedBreakfast, classId, price);
        List<Room> allWithFilters = DaoRoom.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    // todo доделать другие update
    private static void updateRoomHotelId(Long id, Long newHotelId) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющийся hotel_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setHotelId(newHotelId);
            instance.update(room);
        });
        System.out.println("измененный hotel_id: \n" + maybeEntity);
    }

    private static void updateRoomBedsCount(Long id, Integer newBedsCount) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющееся beds_count: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setBedsCount(newBedsCount);
            instance.update(room);
        });
        System.out.println("измененное beds_count: \n" + maybeEntity);
    }

    private static void updateRoomFloor(Long id, Integer newFloor) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющийся floor: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setFloor(newFloor);
            instance.update(room);
        });
        System.out.println("измененный floor: \n" + maybeEntity);
    }

    private static void updateRoomIncludedBreakfast(Long id, Boolean newBreakfastValue) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющийся included_breakfast: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setIncludedBreakfast(newBreakfastValue);
            instance.update(room);
        });
        System.out.println("измененный included_breakfast: \n" + maybeEntity);
    }

    private static void updateRoomClassId(Long id, Long newClassId) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющийся class_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setClassId(newClassId);
            instance.update(room);
        });
        System.out.println("измененный class_id: \n" + maybeEntity);
    }

    private static void updateRoomPrice(Long id, Integer newPrice) {
        DaoRoom instance = DaoRoom.getInstance();
        Optional<Room> maybeEntity = instance.findById(id);
        System.out.println("имеющаяся price: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(room -> {
            room.setPrice(newPrice);
            instance.update(room);
        });
        System.out.println("измененная price: \n" + maybeEntity);
    }

    private static void deleteRoom(long id) {
        DaoRoom daoRoom = DaoRoom.getInstance();
        boolean result = daoRoom.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}
