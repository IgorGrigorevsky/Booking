package entity;

public class ClientRating {
    private Integer id;
    private Integer rating;

    public ClientRating(Integer id, Integer rating) {
        this.id = id;
        this.rating = rating;
    }

    public ClientRating() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
