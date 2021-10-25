package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.search.CriteriaParser;
import ca.uqac.archicompanyproject.domain.search.GenericSpecification;
import ca.uqac.archicompanyproject.domain.search.GenericSpecificationsBuilder;
import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "search",required = false) String search) {
        try {
            if (search == null){
                List<User> users = userService.getUsers();
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            List<User> users = userService.getUsers(this.resolveSpecificationFromInfixExpr(search));
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/:token")
    public ResponseEntity<String> getUserTypeFromToken(@RequestHeader(value = "Authorization", required = false) String token) {
        try {
            if (token != null) {
                String userType = userService.getUserTypeFromToken(token);
                return new ResponseEntity<>(userType, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<User> getUserById(@RequestParam("id") Integer id) {
        try {
            User result = this.userService.findUserById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("email")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        try {
            User user = userService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    protected Specification<User> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), GenericSpecification::new);
    }
}
