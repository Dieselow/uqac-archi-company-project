package ca.uqac.archicompanyproject.domain.users;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoryInterface extends CrudRepository<User,Integer>{
    Optional<User> findByEmail(String email);
}
