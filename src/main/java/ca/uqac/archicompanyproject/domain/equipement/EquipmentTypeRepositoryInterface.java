package ca.uqac.archicompanyproject.domain.equipement;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EquipmentTypeRepositoryInterface extends CrudRepository<EquipmentType, Integer> {
    Optional<EquipmentType> findByName(String name);
}
