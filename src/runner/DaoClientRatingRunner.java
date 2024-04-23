package runner;

import dao.DaoClientRating;
import dto.ClientRatingFilter;
import entity.ClientRating;

import java.util.List;
import java.util.Optional;

public class DaoClientRatingRunner {
    public static void main(String[] args) {

//        deleteClientRating(6);
//        saveClientRating(7);
//            selectAll();

        // TODO глючит с двумя фильтрами
//        selectAllWithFilter(5,0,6,3);

        updateClientRatingRating(6, 10);
    }

    private static void saveClientRating(Integer rating) {
        DaoClientRating daoClientRating = DaoClientRating.getInstance();
        ClientRating clientRating = new ClientRating();

        clientRating.setRating(rating);

        ClientRating savedValue = daoClientRating.save(clientRating);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<ClientRating> all = DaoClientRating.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(Integer limit, int offset, Integer id, int rating) {
        ClientRatingFilter filter = new ClientRatingFilter(limit, offset, id, rating);
        List<ClientRating> allWithFilters = DaoClientRating.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateClientRatingRating(int valueId, int valueRating) {
        DaoClientRating instance = DaoClientRating.getInstance();
        Optional<ClientRating> maybeEntity = instance.findById(valueId);
        System.out.println("имеющийся client_rating_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(rating -> {
            rating.setRating(valueRating);
            instance.update(rating);
        });
        System.out.println("измененный client_rating_id: \n" + maybeEntity);
    }

    private static void deleteClientRating(Integer id) {
        DaoClientRating daoClientRating = DaoClientRating.getInstance();
        boolean result = daoClientRating.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}