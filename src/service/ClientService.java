package service;

import dao.DaoClient;
import dto.client.ClientDto;
import dto.client.ClientFilter;
import dto.client.CreateClientDto;
import entity.Client;
import mapper.CreateClientMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientService {

    // паттерн Singleton
    private static final ClientService INSTANCE = new ClientService();

    private final DaoClient daoClient = DaoClient.getInstance();
    private final CreateClientMapper createClientMapper = CreateClientMapper.getINSTANCE();

    private ClientService() {
    }

    public static ClientService getINSTANCE() {
        return INSTANCE;
    }

    public Long create(CreateClientDto createClientDto) {

        // 1 шаг: validation отсутствует

        // 2 шаг: map Dto -> Entity
        Client client = createClientMapper.mapFrom(createClientDto);

        // 3 шаг: hotelDao.save()
        // после того, как мы преобразовали Dto в Entity, мы можем воспользоваться методом .save()
        // HotelDao.save() -> для того, чтобы сохранить преобразованную сущность из предыдущего шага
        Client savedClient = daoClient.save(client);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedClient.getId();
    }

    public ClientDto save(Client client) {
        Client savedClient = daoClient.save(client);
        return new ClientDto(savedClient.getId(), savedClient.getPersonalInfoId(), savedClient.getClientRatingId());
    }

    public List<ClientDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты Client в ClientDto
        return daoClient.findAll().stream()
                .map(client -> new ClientDto(client.getId(), client.getPersonalInfoId(), client.getClientRatingId()
                )).collect(toList());
    }

    public List<ClientDto> findById(Long id) {
        return daoClient.findById(id).stream()
                .map(client -> new ClientDto(client.getId(), client.getPersonalInfoId(), client.getClientRatingId()
                )).collect(toList());
    }

    public List<ClientDto> findAllWithFilters(ClientFilter filter) {
        return daoClient.findAllWithFilters(filter).stream()
                .map(client -> new ClientDto(client.getId(), client.getPersonalInfoId(), client.getClientRatingId()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoClient.delete(id);
    }
}
