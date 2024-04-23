package entity;

public class Employee {

    private Long id;
//    private Long person_info_id;
    private Long personInfoId;
//    private Long positionId;
    private Long positionId;
//    private Long hotelId;
    private Long hotelId;

    public Employee(Long id, Long personInfoId, Long positionId, Long hotelId) {
        this.id = id;
        this.personInfoId = personInfoId;
        this.positionId = positionId;
        this.hotelId = hotelId;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonInfoId() {
        return personInfoId;
    }

    public void setPersonInfoId(Long personInfoId) {
        this.personInfoId = personInfoId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "EntityEmployee{" +
               "id=" + id +
               ", person_info_id=" + personInfoId +
               ", position_id=" + positionId +
               ", hotel_id=" + hotelId +
               '}';
    }
}
