package entity;

public class EntityHotelStars {

    private Long id;
    private Integer stars;

    public EntityHotelStars(Long id, Integer stars) {
        this.id = id;
        this.stars = stars;
    }

    public EntityHotelStars() {
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
