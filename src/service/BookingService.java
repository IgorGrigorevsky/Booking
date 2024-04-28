package service;

import dao.DaoBooking;
import dto.booking.BookingDto;
import dto.booking.BookingFilter;
import entity.Booking;

import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BookingService {

    // паттерн Singleton
    private static final BookingService INSTANCE = new BookingService();

    private final DaoBooking daoBooking = DaoBooking.getInstance();

    private BookingService() {
    }

    public static BookingService getINSTANCE() {
        return INSTANCE;
    }

    public BookingDto save(Booking booking) {
        Booking savedBooking = daoBooking.save(booking);
        return new BookingDto(savedBooking.getId(), savedBooking.getClientId(), savedBooking.getRoomId(), Timestamp.valueOf(savedBooking.getDateFrom()),
                Timestamp.valueOf(savedBooking.getDateTo()), savedBooking.getIsApproved(), savedBooking.getIsPaid());
    }

    public List<BookingDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты Booking в BookingDto
        return daoBooking.findAll().stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getClientId(), booking.getRoomId(), Timestamp.valueOf(booking.getDateFrom()),
                        Timestamp.valueOf(booking.getDateTo()), booking.getIsApproved(), booking.getIsPaid()
                )).collect(toList());
    }

    public List<BookingDto> findById(Long id) {
        return daoBooking.findById(id).stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getClientId(), booking.getRoomId(), Timestamp.valueOf(booking.getDateFrom()),
                        Timestamp.valueOf(booking.getDateTo()), booking.getIsApproved(), booking.getIsPaid()
                )).collect(toList());
    }

    public List<BookingDto> findAllWithFilters(BookingFilter filter) {
        return daoBooking.findAllWithFilters(filter).stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getClientId(), booking.getRoomId(), Timestamp.valueOf(booking.getDateFrom()),
                        Timestamp.valueOf(booking.getDateTo()), booking.getIsApproved(), booking.getIsPaid()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoBooking.delete(id);
    }
}
