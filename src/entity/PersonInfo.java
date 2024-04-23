package entity;

public class PersonInfo {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;

    public PersonInfo(Long id, String fullName, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public PersonInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
               ", full_name='" + fullName + '\'' +
               ", phone='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
