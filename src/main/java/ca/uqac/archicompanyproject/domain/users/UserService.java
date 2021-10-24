package ca.uqac.archicompanyproject.domain.users;

import javassist.NotFoundException;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User findUserByEmail(String email) throws NotFoundException;
    User findUserById(Integer id) throws NotFoundException;
    List<User> getUsersWithNameContaining(String name);
    boolean checkEmailAlreadyExists(User user);
    List<User> getUsers(Specification specification);
    List<User> getUsers();
}
