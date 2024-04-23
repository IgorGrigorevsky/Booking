package entity;

public class Room {

    private Long id;
//    private Long hotelId;
    private Long hotelId;
    private Integer bedsCount;
    private Integer floor;
    private Boolean includedBreakfast;
//    private Long classId;
    private Long classId;
    private Integer price;

    public Room(Long id, Long hotelId, Integer bedsCount, Integer floor,
                Boolean includedBreakfast, Long classId, Integer price) {
        this.id = id;
        this.hotelId = hotelId;
        this.bedsCount = bedsCount;
        this.floor = floor;
        this.includedBreakfast = includedBreakfast;
        this.classId = classId;
        this.price = price;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(Integer bedsCount) {
        this.bedsCount = bedsCount;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public boolean getIncludedBreakfast() {
        return includedBreakfast;
    }

    public void setIncludedBreakfast(boolean includedBreakfast) {
        this.includedBreakfast = includedBreakfast;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Номер отеля: " +
               "id : " + id +
               ", id отеля номера " + hotelId +
               ", количество кроватей: " + bedsCount +
               ", этаж: " + floor +
               ", завтрак: " + includedBreakfast +
               ", id класса комнаты:  " + classId +
               ", цена :" + price +
               '}';
    }
}
