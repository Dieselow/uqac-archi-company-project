package ca.uqac.archicompanyproject.domain.secretary;

import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SecretaryRepositoryInterface extends CrudRepository<Secretary,Integer> {
    Optional<Secretary> findByEmail(String email);
}
