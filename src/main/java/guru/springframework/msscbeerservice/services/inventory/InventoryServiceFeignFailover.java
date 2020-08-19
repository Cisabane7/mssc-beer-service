package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceFeignFailover implements InventoryServiceFeignClient{
    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        log.debug("Calling failover inventory service");
        return inventoryFailoverFeignClient.getOnHandInventory();
    }
}
