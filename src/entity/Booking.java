package entity;

import java.time.LocalDateTime;

public class Booking {

    private Long id;
    private Long clientId;
    //    private Client clientId;
    private Long roomId;
    //    private Room roomId;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Boolean isApproved;
    private Boolean isPaid;

    public Booking(Long id, Long clientId, Long roomId, LocalDateTime dateFrom,
                   LocalDateTime dateTo, boolean isApproved, boolean isPaid) {
        this.id = id;
        this.clientId = clientId;
        this.roomId = roomId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isApproved = isApproved;
        this.isPaid = isPaid;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "EntityBooking{" +
               "id=" + id +
               ", client_id=" + clientId +
               ", room_id=" + roomId +
               ", date_from=" + dateFrom +
               ", date_to=" + dateTo +
               ", is_approved=" + isApproved +
               ", is_paid=" + isPaid +
               '}';
    }
}
