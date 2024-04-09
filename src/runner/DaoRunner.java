package runner;

import dao.DaoRoom;
import dto.RoomFilter;
import entity.EntityRoom;

import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {


        // CREATE
        saveRoom(1L, 3, 5, true, 3L, 15000);

        // READ
        selectAll();
        selectAllWithFilter(5, 0, null, null, null, 2, null, null, null);

        // UPDATE
        updateRoomPrice();

        // DELETE
        deleteRoom(1L);


    }


    private static void updateRoomPrice() {
        DaoRoom instance = DaoRoom.getINSTANCE();
        Optional<EntityRoom> maybeEntityRoom = instance.findById(21L);
        System.out.println(maybeEntityRoom);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntityRoom.ifPresent(room -> {
            room.setPrice(10888);
            instance.update(room);
        });
    }

    private static void deleteRoom(long id) {
        DaoRoom daoRoom = DaoRoom.getINSTANCE();
        boolean deleteRoom = daoRoom.delete(id);
        System.out.println(deleteRoom);
    }

    private static void saveRoom(long hotelId, int bedsCount, int floor, boolean includedBreakfast, long classId, int price) {
        DaoRoom daoRoom = DaoRoom.getINSTANCE();
        EntityRoom room = new EntityRoom();
        room.setHotel_id(hotelId);
        room.setBeds_count(bedsCount);
        room.setFloor(floor);
        room.setIncluded_breakfast(includedBreakfast);
        room.setClass_id(classId);
        room.setPrice(price);
        EntityRoom savedRoom = daoRoom.save(room);
        System.out.println(savedRoom);
    }

    private static void selectAll() {
        List<EntityRoom> all = DaoRoom.getINSTANCE().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(int limit, int offset, Long id, Long hotelId, Integer bedsCount,
                                            int floor, Boolean includedBreakfast, Long classId, Integer price) {
        RoomFilter roomFilter = new RoomFilter(limit, offset, id, hotelId,
                bedsCount, floor, includedBreakfast, classId, price);
        List<EntityRoom> allWithFilters = DaoRoom.getINSTANCE().findAllWithFilters(roomFilter);
        System.out.println(allWithFilters);
    }
}
