package ca.uqac.archicompanyproject.domain.caregiver;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CaregiverRepositoryInterface extends CrudRepository<Caregiver,Integer> {
    Optional<Caregiver> findByEmail(String email);
}
