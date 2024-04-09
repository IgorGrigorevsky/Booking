package entity;

import java.sql.Timestamp;

public class EntityBooking {

    private Long id;
    private Long client_id;
    private Long room_id;
    private Timestamp date_from;
    private Timestamp date_to;
    private boolean is_approved;
    private boolean is_paid;

    public EntityBooking(Long id, Long client_id, Long room_id, Timestamp date_from,
                         Timestamp date_to, boolean is_approved, boolean is_paid) {
        this.id = id;
        this.client_id = client_id;
        this.room_id = room_id;
        this.date_from = date_from;
        this.date_to = date_to;
        this.is_approved = is_approved;
        this.is_paid = is_paid;
    }

    public EntityBooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Timestamp getDate_from() {
        return date_from;
    }

    public void setDate_from(Timestamp date_from) {
        this.date_from = date_from;
    }

    public Timestamp getDate_to() {
        return date_to;
    }

    public void setDate_to(Timestamp date_to) {
        this.date_to = date_to;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    @Override
    public String toString() {
        return "EntityBooking{" +
               "id=" + id +
               ", client_id=" + client_id +
               ", room_id=" + room_id +
               ", date_from=" + date_from +
               ", date_to=" + date_to +
               ", is_approved=" + is_approved +
               ", is_paid=" + is_paid +
               '}';
    }
}
