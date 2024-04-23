package entity;

public class Hotel {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    //    private Long starsId;
    private Long starsId;

    public Hotel(Long id, String name, String address, String phone, String email, Long starsId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.starsId = starsId;
    }

    public Hotel() {
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

    public Long getStarsId() {
        return starsId;
    }

    public void setStarsId(Long starsId) {
        this.starsId = starsId;
    }

    @Override
    public String toString() {
        return "EntityHotel " + "id = " + id + ", название - " + name +
               ", адрес - " + address +
               ", телефон - " + phone  +
               ", email - " + email +
               ", stars_id - " + starsId +
               "}\n";
    }
}
