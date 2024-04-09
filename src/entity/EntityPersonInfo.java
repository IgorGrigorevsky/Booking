package entity;

public class EntityPersonInfo {

    private Long id;
    private String full_name;
    private String phone_number;
    private String email;

    public EntityPersonInfo(Long id, String full_name, String phone_number, String email) {
        this.id = id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public EntityPersonInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EntityPersonInfo{" +
               "id=" + id +
               ", full_name='" + full_name + '\'' +
               ", phone='" + phone_number + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
