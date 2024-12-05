package runner;

import dao.DaoBooking;
import dto.booking.BookingFilter;
import entity.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DaoBookingRunner {
    public static void main(String[] args) {

        // CREATE
        saveBooking(3L, 2L, LocalDate.of(2024, 5, 9),
                LocalDate.of(2024, 5, 15), null, null);

        // READ
        selectAll();
        selectAllWithFilter(5, 0, null, null, null,
                LocalDate.of(2024, 5, 30),
                LocalDate.of(2024, 6, 30), null, null);

        // DELETE
        deleteBooking(11L);

    }

    private static void saveBooking(Long clientId, Long roomId, LocalDate dateFrom, LocalDate dateTo, Boolean isApproved, Boolean isPaid) {
        DaoBooking daoBooking = DaoBooking.getInstance();
        Booking booking = new Booking();

        booking.setClientId(clientId);
        booking.setRoomId(roomId);
        booking.setDateFrom(dateFrom);
        booking.setDateTo(dateTo);
        booking.setIsApproved(false);
        booking.setIsPaid(false);

        Booking savedValue = daoBooking.save(booking);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<Booking> all = DaoBooking.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(Integer limit, int offset, Long id, Long client_id, Long room_id,
                                            LocalDate dateFrom, LocalDate dateTo, Boolean is_approved, Boolean is_paid) {
        BookingFilter filter = new BookingFilter(limit, offset, id, client_id, room_id,
                dateFrom, dateTo, is_approved, is_paid);
        List<Booking> allWithFilters = DaoBooking.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateBookingClientId(Long id, Long newClientId) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся client_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setClientId(newClientId);
            instance.update(booking);
        });
        System.out.println("измененный client_id: \n" + maybeEntity);
    }

    private static void updateBookingRoomId(Long id, Long newRoomId) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся room_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setRoomId(newRoomId);
            instance.update(booking);
        });
        System.out.println("измененный room_id: \n" + maybeEntity);
    }

    private static void updateBookingDateFrom(Long id, LocalDate newDateFrom) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся date_from: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setDateFrom(newDateFrom);
            instance.update(booking);
        });
        System.out.println("измененный date_from: \n" + maybeEntity);
    }

    private static void updateBookingDateTo(Long id, LocalDate newDateTo) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся date_to: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setDateTo(newDateTo);
            instance.update(booking);
        });
        System.out.println("измененный date_to: \n" + maybeEntity);
    }

    private static void updateBookingIsApproved(Long id, Boolean newIsApproved) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся is_approved: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setIsApproved(newIsApproved);
            instance.update(booking);
        });
        System.out.println("измененный is_approved: \n" + maybeEntity);
    }

    private static void updateBookingIsPaid(Long id, Boolean newIsPaid) {
        DaoBooking instance = DaoBooking.getInstance();
        Optional<Booking> maybeEntity = instance.findById(id);
        System.out.println("имеющийся is_paid: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(booking -> {
            booking.setIsPaid(newIsPaid);
            instance.update(booking);
        });
        System.out.println("измененный is_paid: \n" + maybeEntity);
    }

    private static void deleteBooking(Long id) {
        DaoBooking daoBooking = DaoBooking.getInstance();
        boolean result = daoBooking.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}
