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
public class HotelStars {

    private Long id;
    private Integer stars;

    @Override
    public String toString() {
        return "EntityHotelStars{" +
               "id=" + id +
               ", stars=" + stars +
               '}';
    }
}
