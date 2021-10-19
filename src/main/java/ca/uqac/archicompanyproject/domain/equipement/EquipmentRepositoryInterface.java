package ca.uqac.archicompanyproject.domain.equipement;

import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepositoryInterface extends CrudRepository<Equipment, Integer> {
    Iterable<Equipment> findByEquipmentType(EquipmentType equipmentType);
}
