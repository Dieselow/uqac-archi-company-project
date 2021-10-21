package ca.uqac.archicompanyproject.domain.consumable;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ConsumableRepositoryInterface extends CrudRepository<Consumable,Integer> {
    Optional<Consumable> findByConsumableType(ConsumableType consumableType);
}

