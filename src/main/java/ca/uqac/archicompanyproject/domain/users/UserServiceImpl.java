package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import javassist.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepositoryInterface userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptEncoder;

    public UserServiceImpl(UserRepositoryInterface userRepository, RoleRepository roleRepository, PasswordEncoder bCryptEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public User addUser(User user) {
        if (user.getRole() == null) {
            user.setRole(this.roleRepository.findByName(Roles.USER.toString()));
        }

        return this.userRepository.save(
                User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .address(user.getAddress())
                        .username(user.getUsername())
                        .licenceNumber(user.getLicenceNumber())
                        .password(bCryptEncoder.encode(user.getPassword()))
                        .role(user.getRole())
                        .dateOfBirth(user.getDateOfBirth())
                        .salary(user.getSalary())
                        .workSchedule(user.getWorkSchedule())
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
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return buildUserForAuthentication(user.get(),getUserAuthority(user.get().getRole()));
        }
        throw new UsernameNotFoundException("User with email " + email + "not found");
    }

    private List<GrantedAuthority> getUserAuthority(Role userRole) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add((new SimpleGrantedAuthority(userRole.getName())));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
