package service;

import dao.DaoClientRating;
import dto.clientRating.ClientRatingDto;
import dto.clientRating.ClientRatingFilter;
import dto.clientRating.CreateClientRatingDto;
import entity.ClientRating;
import mapper.CreateClientRatingMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientRatingService {

    // паттерн Singleton
    private static final ClientRatingService INSTANCE = new ClientRatingService();

    private final DaoClientRating daoClientRating = DaoClientRating.getInstance();
    private final CreateClientRatingMapper createClientRatingMapper = CreateClientRatingMapper.getINSTANCE();

    private ClientRatingService() {
    }

    public static ClientRatingService getINSTANCE() {
        return INSTANCE;
    }

    public Integer create(CreateClientRatingDto createClientRatingDto) {

        // 1 шаг: validation отсутствует

        // 2 шаг: map Dto -> Entity
        ClientRating clientRating = createClientRatingMapper.mapFrom(createClientRatingDto);

        // 3 шаг: hotelDao.save()
        // после того, как мы преобразовали Dto в Entity, мы можем воспользоваться методом .save()
        // HotelDao.save() -> для того, чтобы сохранить преобразованную сущность из предыдущего шага
        ClientRating savedClientRating = daoClientRating.save(clientRating);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedClientRating.getId();
    }

    public ClientRatingDto save(ClientRating clientRating) {
        ClientRating savedClientRating = daoClientRating.save(clientRating);
        return new ClientRatingDto(savedClientRating.getId(), savedClientRating.getRating());
    }

    public List<ClientRatingDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты ClientRating в ClientRatingDto
        return daoClientRating.findAll().stream()
                .map(clientRating -> new ClientRatingDto(clientRating.getId(), clientRating.getRating()
                )).collect(toList());
    }

    public List<ClientRatingDto> findById(Integer id) {
        return daoClientRating.findById(id).stream()
                .map(clientRating -> new ClientRatingDto(clientRating.getId(), clientRating.getRating()
                )).collect(toList());
    }

    public List<ClientRatingDto> findAllWithFilters(ClientRatingFilter filter) {
        return daoClientRating.findAllWithFilters(filter).stream()
                .map(clientRating -> new ClientRatingDto(clientRating.getId(), clientRating.getRating()
                )).collect(toList());
    }

    public boolean delete(Integer id) {
        return daoClientRating.delete(id);
    }
}
