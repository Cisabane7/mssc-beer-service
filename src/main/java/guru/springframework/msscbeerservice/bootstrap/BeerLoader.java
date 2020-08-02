package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.sfg.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by jt on 2019-05-17.
 */
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        if ( beerRepository.count() == 0) {
            buildData();
        }
    }

    private void buildData() {
        Beer b1 = Beer.builder()
                .beerName("Mango Bobs").beerStyle(BeerStyleEnum.IPA.toString()).minOnHand(12)
                .quantityToBrew(200).price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC).version(1L).build();
        Beer b2 = Beer.builder()
                .beerName("Galaxy Cat").beerStyle("PALE_ALE").minOnHand(12)
                .quantityToBrew(200).price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC).version(1L).build();
        Beer b3 = Beer.builder()
                .beerName("inball Porter").beerStyle("PORTER").minOnHand(12)
                .quantityToBrew(200).price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC).version(1L).build();
        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);
    }


}
