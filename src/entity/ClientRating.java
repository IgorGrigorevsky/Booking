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
public class ClientRating {

    private Integer id;
    private Integer rating;



    @Override
    public String toString() {
        return "EntityClientRating{" +
               "id=" + id +
               ", rating=" + rating +
               '}';
    }
}
