package ca.uqac.archicompanyproject.infra.web.authentication;

import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserServiceImpl;
import ca.uqac.archicompanyproject.infra.dto.authentication.LoginDTO;
import ca.uqac.archicompanyproject.infra.dto.authentication.LoginReponseDTO;
import ca.uqac.archicompanyproject.security.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final TokenProvider tokenProvider;
    private final UserServiceImpl userService;
    private final AuthenticationManagerBuilder authenticationManager;

    public AuthenticationController(TokenProvider tokenProvider, UserServiceImpl userService, AuthenticationManagerBuilder authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> login(@RequestBody LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword());


        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

        String token = tokenProvider.generateToken(authentication);

        LoginReponseDTO response = new LoginReponseDTO();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        User _userExist = null;
        _userExist = userService.findUserByEmail(user.getEmail());
        if (_userExist == null) {
            User _user = userService.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
