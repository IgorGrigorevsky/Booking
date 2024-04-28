package runner;

import dao.DaoHotel;
import dto.hotel.HotelFilter;
import entity.Hotel;

import java.util.List;
import java.util.Optional;

public class DaoHotelRunner {

    public static void main(String[] args) {

        // CREATE
        saveHotel("Москва", "г. Москва, ул. Беговая, д. 3", "74950007788", "moscow@yandex.ru", 3L);

        // READ
        selectAll();
        selectAllWithFilter(5, 0, null, "е", null, null, null, 1L);

        // UPDATE
        updateDaoHotelName(6L, "Царь-Пушка");
        updateDaoHotelAddress(6L, "г. Москва, ул. Окружная, д. 7");
        updateDaoHotelPhone(6L, "79533333333");
        updateDaoHotelEmail(6L, "pushka@yahoo.com");
        updateDaoHotelStarsId(6L, 3L);

        // DELETE
        deleteHotel(6L);
    }

    private static void saveHotel(String name, String address, String phone, String email, Long stars_id) {
        if (DaoHotel.checkName(name)
            && DaoHotel.checkAddress(address)
            && DaoHotel.checkPhone(phone)
            && DaoHotel.checkEmail(email)) {

            DaoHotel daoHotel = DaoHotel.getInstance();
            Hotel hotel = new Hotel();

            hotel.setName(name);
            hotel.setAddress(address);
            hotel.setPhone(phone);
            hotel.setEmail(email);
            hotel.setStarsId(stars_id);

            Hotel savedValue = daoHotel.save(hotel);
            System.out.println("Сохраненная запись: \n" + savedValue);
        } else {
            System.out.println("введен некорректный формат ФИО, адреса, номера или email");
            System.out.println("пожалуйста, используйте следующий пример: \n Метрополис \n г. Москва, ул. Пятницкая, д. 3 " +
                               "(при наличии добавьте - корп. 1 \n 79101234567 или 74953456789 \n java@gmail.com");
        }
    }

    private static void selectAll() {
        List<Hotel> all = DaoHotel.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(Integer limit, int offset, Long id, String name, String address, String phone,
                                            String email, Long starsId) {

        HotelFilter filter = new HotelFilter(limit, offset, id, name, address, phone, email, starsId);
        List<Hotel> allWithFilters = DaoHotel.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateDaoHotelName(Long id, String newName) {
        if (DaoHotel.checkName(newName)) {
            DaoHotel instance = DaoHotel.getInstance();
            Optional<Hotel> maybeEntity = instance.findById(id);
            System.out.println("имеющийся name: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(hotel -> {
                hotel.setName(newName);
                instance.update(hotel);
            });
            System.out.println("измененный name: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат названия: " + newName);
            System.out.println("пожалуйста, используйте следующий пример: Метрополис");
        }
    }

    private static void updateDaoHotelAddress(Long id, String newAddress) {

        if (DaoHotel.checkAddress(newAddress)) {

            DaoHotel instance = DaoHotel.getInstance();
            Optional<Hotel> maybeEntity = instance.findById(id);
            System.out.println("имеющийся phone: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(hotel -> {
                hotel.setAddress(newAddress);
                instance.update(hotel);
            });
            System.out.println("измененный phone: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат адреса - " + newAddress);
            System.out.println("пожалуйста, используйте следующий пример: г. Москва, ул. Пятницкая, д. 3");
        }
    }

    private static void updateDaoHotelPhone(Long id, String newPhone) {

        if (DaoHotel.checkPhone(newPhone)) {
            DaoHotel instance = DaoHotel.getInstance();
            Optional<Hotel> maybeEntity = instance.findById(id);
            System.out.println("имеющийся phone: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(hotel -> {
                hotel.setPhone(newPhone);
                instance.update(hotel);
            });
            System.out.println("измененный phone: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат номера - " + newPhone);
            System.out.println("пожалуйста, используйте следующий пример: 79101234567 или 74953456789");
        }
    }

    private static void updateDaoHotelEmail(Long id, String newEmail) {

        if (DaoHotel.checkEmail(newEmail)) {

            DaoHotel instance = DaoHotel.getInstance();
            Optional<Hotel> maybeEntity = instance.findById(id);
            System.out.println("имеющийся email: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(hotel -> {
                hotel.setEmail(newEmail);
                instance.update(hotel);
            });
            System.out.println("измененный email: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат email - " + newEmail);
            System.out.println("пожалуйста, используйте следующий пример: java@gmail.com");
        }
    }

    private static void updateDaoHotelStarsId(Long id, Long newStarsId) {

        DaoHotel instance = DaoHotel.getInstance();
        Optional<Hotel> maybeEntity = instance.findById(id);
        System.out.println("имеющийся stars_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(hotel -> {
            hotel.setStarsId(newStarsId);
            instance.update(hotel);
        });
        System.out.println("измененный stars_id: \n" + maybeEntity);
    }

    private static void deleteHotel(Long id) {
        DaoHotel daoHotel = DaoHotel.getInstance();
        boolean result = daoHotel.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}