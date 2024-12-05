package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private Long id;
//    private Long personalInfoId;
    private Long personalInfoId;
//    private Long client_rating_id;
    private Long clientRatingId;

    @Override
    public String toString() {
        return "Клиент {" +
               "id =" + id +
               ", person_info_id =" + personalInfoId +
               ", client_rating_id =" + clientRatingId +
               '}';
    }
}
