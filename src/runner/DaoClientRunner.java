package runner;

import dao.DaoClient;
import dto.client.ClientFilter;
import entity.Client;

import java.util.List;
import java.util.Optional;

public class DaoClientRunner {

    public static void main(String[] args) {

        // CREATE
        saveClient(5L, 9L);

        // READ ALL
        selectAll();
        selectAllWithFilter(5, 0, 3L, 13L, 2L);

        // UPDATE
        updateClientRatingId(4L);
        updateClientInfoId(9L);

        // DELETE
        deleteClient(8L);
    }

    private static void saveClient(long clientRatingId, long personalInfoId) {
        DaoClient daoClient = DaoClient.getInstance();
        Client client = new Client();

        client.setClientRatingId(clientRatingId);
        client.setPersonalInfoId(personalInfoId);

        Client savedValue = daoClient.save(client);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<Client> all = DaoClient.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(int limit, int offset, Long id, Long personInfoId, Long clientRatingId) {
        ClientFilter filter = new ClientFilter(limit, offset, id, personInfoId, clientRatingId);
        List<Client> allWithFilters = DaoClient.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateClientRatingId(Long ratingId) {
        DaoClient instance = DaoClient.getInstance();
        Optional<Client> maybeEntity = instance.findById(6L);
        System.out.println("имеющийся client_rating_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(client -> {
            client.setClientRatingId(ratingId);
            instance.update(client);
        });
        System.out.println("измененный client_rating_id: \n" + maybeEntity);
    }

    private static void updateClientInfoId(Long clientInfoId) {
        DaoClient instance = DaoClient.getInstance();
        Optional<Client> maybeEntity = instance.findById(6L);
        System.out.println("имеющийся client_info_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(client -> {
            client.setPersonalInfoId(clientInfoId);
            instance.update(client);
        });
        System.out.println("измененный client_info_id: \n" + maybeEntity);
    }

    private static void deleteClient(long id) {
        DaoClient daoClient = DaoClient.getInstance();
        boolean result = daoClient.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}

