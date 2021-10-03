package ca.uqac.archicompanyproject.domain.users;

import javassist.NotFoundException;

public interface UserService {
    //A ne pas utiliser ?
    User addUser(User user);
    User findUserByEmail(String email) throws NotFoundException;
    User findUserById(Integer id) throws NotFoundException;
    Iterable<User> getUsers();
}
