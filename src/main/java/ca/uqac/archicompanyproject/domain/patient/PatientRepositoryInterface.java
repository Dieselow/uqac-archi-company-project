package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepositoryInterface extends CrudRepository<Patient,Integer> {
    Optional<Patient> findByEmail(String email);
}
