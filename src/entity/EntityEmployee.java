package entity;

public class EntityEmployee {

    private Long id;
    private Long person_info_id;
    private Long position_id;
    private Long hotel_id;

    public EntityEmployee(Long id, Long person_info_id, Long position_id, Long hotel_id) {
        this.id = id;
        this.person_info_id = person_info_id;
        this.position_id = position_id;
        this.hotel_id = hotel_id;
    }

    public EntityEmployee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPerson_info_id() {
        return person_info_id;
    }

    public void setPerson_info_id(Long person_info_id) {
        this.person_info_id = person_info_id;
    }

    public Long getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Long position_id) {
        this.position_id = position_id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "EntityEmployee{" +
               "id=" + id +
               ", person_info_id=" + person_info_id +
               ", position_id=" + position_id +
               ", hotel_id=" + hotel_id +
               '}';
    }
}
