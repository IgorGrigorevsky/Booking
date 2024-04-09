package entity;

public class EntityRoom {

    private Long id;
    private Long hotel_id;
    private Integer beds_count;
    private Integer floor;
    private boolean included_breakfast;
    private Long class_id;
    private Integer price;

    public EntityRoom(Long id, Long hotel_id, Integer beds_count, Integer floor,
                      boolean included_breakfast, Long class_id, Integer price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.beds_count = beds_count;
        this.floor = floor;
        this.included_breakfast = included_breakfast;
        this.class_id = class_id;
        this.price = price;
    }

    public EntityRoom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Integer getBeds_count() {
        return beds_count;
    }

    public void setBeds_count(Integer beds_count) {
        this.beds_count = beds_count;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public boolean isIncluded_breakfast() {
        return included_breakfast;
    }

    public void setIncluded_breakfast(boolean included_breakfast) {
        this.included_breakfast = included_breakfast;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EntityRoom{" +
               "id=" + id +
               ", hotel_id=" + hotel_id +
               ", beds_count=" + beds_count +
               ", floor=" + floor +
               ", included_breakfast=" + included_breakfast +
               ", class_id=" + class_id +
               ", price=" + price +
               '}';
    }
}
