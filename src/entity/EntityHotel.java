package entity;

public class EntityHotel {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Long stars_id;

    public EntityHotel(Long id, String name, String address, String phone, String email, Long stars_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.stars_id = stars_id;
    }

    public EntityHotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStars_id() {
        return stars_id;
    }

    public void setStars_id(Long stars_id) {
        this.stars_id = stars_id;
    }

    @Override
    public String toString() {
        return "EntityHotel{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", stars_id=" + stars_id +
               '}';
    }
}
