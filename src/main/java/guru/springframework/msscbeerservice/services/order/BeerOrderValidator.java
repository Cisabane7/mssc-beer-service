package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class BeerOrderValidator {
    private final BeerRepository beerRepository;

    public Boolean isValid(BeerOrderDto beerOrderDto) {
        AtomicInteger counter = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().stream().takeWhile(bol -> {
            AtomicReference<Boolean> result = new AtomicReference<>(false);
            beerRepository.findByUpc(bol.getUpc()).ifPresent(beer -> result.set(true));
            return result.get();
        }).forEach(beerOrderLineDto -> counter.incrementAndGet());
        return beerOrderDto.getBeerOrderLines().size() == counter.get();
    }
}
