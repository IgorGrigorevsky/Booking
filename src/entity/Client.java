package entity;

public class Client {

    private Long id;
//    private Long personalInfoId;
    private Long personalInfoId;
//    private Long client_rating_id;
    private Long clientRatingId;

    public Client(Long id, Long personalInfoId, Long clientRatingId) {
        this.id = id;
        this.personalInfoId = personalInfoId;
        this.clientRatingId = clientRatingId;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public Long getClientRatingId() {
        return clientRatingId;
    }

    public void setClientRatingId(Long clientRatingId) {
        this.clientRatingId = clientRatingId;
    }

    @Override
    public String toString() {
        return "Клиент {" +
               "id =" + id +
               ", person_info_id =" + personalInfoId +
               ", client_rating_id =" + clientRatingId +
               '}';
    }
}
