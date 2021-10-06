package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = (List<User>) userService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("email")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        try {
            User user = userService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
