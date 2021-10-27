package ca.uqac.archicompanyproject.domain.caregiver;

import ca.uqac.archicompanyproject.domain.users.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CaregiverRepositoryInterface extends CrudRepository<Caregiver,Integer>, JpaSpecificationExecutor<Caregiver> {
    Optional<Caregiver> findByEmail(String email);
}
