package entity;

public class EntityClientRating {
    private Long id;
    private Integer rating;

    public EntityClientRating(Long id, Integer rating) {
        this.id = id;
        this.rating = rating;
    }

    public EntityClientRating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "EntityClientRating{" +
               "id=" + id +
               ", rating=" + rating +
               '}';
    }
}
