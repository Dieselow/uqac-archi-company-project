package ca.uqac.archicompanyproject.domain.consumableType;

import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface ConsumableTypeRepositoryInterface extends CrudRepository<ConsumableType,Integer> {
    Optional<ConsumableType> findByName(String name);
}