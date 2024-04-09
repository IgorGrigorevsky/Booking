package entity;

public class EntityRoomClass {

    private Long id;
    private String roomClass;

    public EntityRoomClass(Long id, String roomClass) {
        this.id = id;
        this.roomClass = roomClass;
    }

    public EntityRoomClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    @Override
    public String toString() {
        return "EntityRoomClass{" +
               "id=" + id +
               ", roomClass='" + roomClass + '\'' +
               '}';
    }
}
