package ca.uqac.archicompanyproject.domain.consumable;

import ca.uqac.archicompanyproject.domain.consumableType.ConsumableType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ConsumableRepositoryInterface extends CrudRepository<Consumable,Integer> {
    Optional<Consumable> findByConsumableType(ConsumableType consumableType);
}

