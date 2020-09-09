package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile({"local-discovery", "digitalocean"})
public class BeerInventoryServiceFeign implements BeerInventoryService {
    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service w/Feign - BeerId: " + beerId);

        int onHand = 0;

        try {
            ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);

            if (responseEntity.getBody() != null && responseEntity.getBody().size() > 0) {
                log.debug("Inventory found, summing inventory");

                onHand = Objects.requireNonNull(responseEntity.getBody())
                        .stream()
                        .mapToInt(BeerInventoryDto::getQuantityOnHand)
                        .sum();
            }
        } catch (Exception e) {
            log.error("Exception thrown calling inventory service", e);
            throw e;
        }

        log.debug("BeerId: " + beerId + " On hand is: " + onHand);

        return onHand;
    }
}
