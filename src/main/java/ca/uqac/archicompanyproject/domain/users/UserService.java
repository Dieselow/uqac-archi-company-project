package ca.uqac.archicompanyproject.domain.users;

import javassist.NotFoundException;

import java.util.Optional;

public interface UserService {
    User addUser(User user);
    User findUserByEmail(String email) throws NotFoundException;
    User findUserById(Integer id) throws NotFoundException;
    Iterable<User> getUsers();
}
