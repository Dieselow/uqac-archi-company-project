package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    //Class repositories
    private final UserRepositoryInterface userRepository;

    //Other
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptEncoder;

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
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
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
    public List getUsers(Specification specification) {
        return userRepository.findAll(specification);
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
