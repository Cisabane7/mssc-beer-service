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
@Profile("local-discovery")
public class BeerInventoryServiceFeign implements BeerInventoryService {
    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling inventory service");
        ResponseEntity<List<BeerInventoryDto>> listBeers = inventoryServiceFeignClient.getOnHandInventory(beerId);
        return Objects.requireNonNull(listBeers.getBody())
                .stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
    }
}
