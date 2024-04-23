package entity;

public class HotelStars {

    private Long id;
    private Integer stars;

    public HotelStars(Long id, Integer stars) {
        this.id = id;
        this.stars = stars;
    }

    public HotelStars() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "EntityHotelStars{" +
               "id=" + id +
               ", stars=" + stars +
               '}';
    }
}
