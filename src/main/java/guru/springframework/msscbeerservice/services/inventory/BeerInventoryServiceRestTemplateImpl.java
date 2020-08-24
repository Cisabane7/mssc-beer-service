package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = true)
@Slf4j
@Profile("!local-discovery")
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    public static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private RestTemplate restTemplate;

    private String beerInventoryServiceHost;

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                                @Value("${sfg.brewery.inventory-user}") String inventoryUser,
                                                @Value("${sfg.brewery.inventory-pwd}") String inventoryPwd) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(inventoryUser, inventoryPwd)
                .build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling inventory service");
        ResponseEntity<List<BeerInventoryDto>> listBeers = restTemplate.exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, (Object) beerId);
        return Objects.requireNonNull(listBeers.getBody())
                .stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
    }

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }
}
