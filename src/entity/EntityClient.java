package entity;

public class EntityClient {

    private Long id;
    private Long personal_info_id;
    private Long client_rating_id;

    public EntityClient(Long id, Long personal_info_id, Long client_rating_id) {
        this.id = id;
        this.personal_info_id = personal_info_id;
        this.client_rating_id = client_rating_id;
    }

    public EntityClient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonal_info_id() {
        return personal_info_id;
    }

    public void setPersonal_info_id(Long personal_info_id) {
        this.personal_info_id = personal_info_id;
    }

    public Long getClient_rating_id() {
        return client_rating_id;
    }

    public void setClient_rating_id(Long client_rating_id) {
        this.client_rating_id = client_rating_id;
    }

    @Override
    public String toString() {
        return "EntityClient{" +
               "id=" + id +
               ", personal_info_id=" + personal_info_id +
               ", client_rating_id=" + client_rating_id +
               '}';
    }
}
