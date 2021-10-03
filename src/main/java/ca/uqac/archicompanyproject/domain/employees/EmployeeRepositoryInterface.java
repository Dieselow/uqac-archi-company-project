package ca.uqac.archicompanyproject.domain.employees;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepositoryInterface extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
}
