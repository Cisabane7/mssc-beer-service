package guru.springframework.msscbeerservice.repositories;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageRequest);

    Page<Beer> findAllByBeerName(String beerName, Pageable pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageRequest);

    Optional<Beer> findByUpc(String upc);
}
