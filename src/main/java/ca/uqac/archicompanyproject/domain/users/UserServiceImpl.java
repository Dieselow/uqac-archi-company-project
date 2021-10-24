package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.security.TokenProvider;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    //Class repositories
    private final UserRepositoryInterface userRepository;

    //Other
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public User addUser(User user) {
        if (user.getRoles() == null) {
            Set<Role> userRoles = new HashSet<>();
            userRoles.add( this.roleRepository.findByName(Roles.USER.toString()));
            user.setRoles(userRoles);
        }

        return this.userRepository.save(
                User.userBuilder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .address(user.getAddress())
                        .username(user.getUsername())
                        .password(bCryptEncoder.encode(user.getPassword()))
                        .roles(user.getRoles())
                        .dateOfBirth(user.getDateOfBirth())
                        .build()
        );

    }

    @Override
    public User findUserByEmail(String email) throws NotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFoundException("User with email "+ email + " not found");
    }

    @Override
    public User findUserById(Integer id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFoundException("User with id" + id + "not found");
    }

    @Override
    public String getUserTypeFromToken(String token) throws NotFoundException{
        token = token.replace("Bearer ","");
        return findUserByEmail(this.tokenProvider.getUsernameFromToken(token)).getClass().getSimpleName();
    }

    @Override
    public List<User> getUsersWithNameContaining(String name){
        //En vrai y'a surement moyen de le faire avec tes criterias Louis mais bon euh pas le  temps
        List<User> firstname = (List<User>) userRepository.findByFirstNameContains(name);
        List<User> result = (List<User>) userRepository.findByLastNameContains(name);

        result.addAll(firstname);
        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public boolean checkEmailAlreadyExists(User user){
        try {
            //On check qu'on a pas update l'email vers un existant
            User userWithEmail = this.findUserByEmail(user.getEmail());
            if (userWithEmail.getID() != user.getID()){
                return true;
            }
            return false;
        } catch (Exception ex) {
            //Il n'existe pas de user avec cet email, on continue
            return false;
        }
    }

    @Override
    public List getUsers(Specification specification) {
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return buildUserForAuthentication(user.get(),getAuthority(user.get()));
        }
        throw new UsernameNotFoundException("User with email " + email + "not found");
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    private UserDetails buildUserForAuthentication(User user, Set<SimpleGrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
