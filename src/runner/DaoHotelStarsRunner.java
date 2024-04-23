package runner;

import dao.DaoHotelStars;
import dto.HotelStarsFilter;
import entity.HotelStars;

import java.util.List;
import java.util.Optional;

public class DaoHotelStarsRunner {


    public static void main(String[] args) {

        // CREATE
        saveHotelStars(7);

        //  READ
        selectAll();
        selectAllWithFilter(5, 0, null, 3);

        // UPDATE
        updateHotelStarsStar(7L, 10);

        // DELETE
        deleteHotelStars(7L);
    }

    private static void saveHotelStars(Integer starValue) {
        DaoHotelStars daoHotelStars = DaoHotelStars.getInstance();
        HotelStars hotelStars = new HotelStars();

        hotelStars.setStars(starValue);

        HotelStars savedValue = daoHotelStars.save(hotelStars);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<HotelStars> all = DaoHotelStars.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(Integer limit, Integer offset, Long id, Integer stars) {
        HotelStarsFilter filter = new HotelStarsFilter(limit, offset, id, stars);
        List<HotelStars> allWithFilters = DaoHotelStars.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateHotelStarsStar(Long valueId, Integer valueStar) {
        DaoHotelStars instance = DaoHotelStars.getInstance();
        Optional<HotelStars> maybeEntity = instance.findById(valueId);
        System.out.println("имеющийся star: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(hotelStars -> {
            hotelStars.setStars(valueStar);
            instance.update(hotelStars);
        });
        System.out.println("измененный star: \n" + maybeEntity);
    }

    private static void deleteHotelStars(Long id) {
        DaoHotelStars daoHotelStars = DaoHotelStars.getInstance();
        boolean result = daoHotelStars.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}
