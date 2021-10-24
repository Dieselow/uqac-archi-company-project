package ca.uqac.archicompanyproject.domain.users;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoryInterface extends CrudRepository<User,Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    Iterable<User> findByFirstNameContains(String name);
    Iterable<User> findByLastNameContains(String name);
}
