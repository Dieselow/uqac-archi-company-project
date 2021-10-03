package ca.uqac.archicompanyproject.domain.secretary;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SecretaryRepositoryInterface extends CrudRepository<Secretary,Integer> {
    Optional<Secretary> findByEmail(String email);
}
