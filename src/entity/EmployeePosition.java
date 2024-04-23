package entity;

public class EmployeePosition {
    private Long id;
    private String position;

    public EmployeePosition(Long id, String position) {
        this.id = id;
        this.position = position;
    }

    public EmployeePosition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EntityEmployeePosition{" +
               "id=" + id +
               ", position='" + position + '\'' +
               '}';
    }
}
