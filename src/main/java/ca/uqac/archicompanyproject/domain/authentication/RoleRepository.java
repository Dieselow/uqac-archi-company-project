package ca.uqac.archicompanyproject.domain.authentication;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String role);
}
