package ca.uqac.archicompanyproject.domain.secretary;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.security.TokenProvider;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SecretaryServiceImpl implements  SecretaryService{

    private final SecretaryRepositoryInterface secretaryRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        return secretaryRepository.save(secretary);
    }

    @Override
    public Secretary addSecretary(Secretary secretary) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add( this.roleRepository.findByName(Roles.SECRETARY.name()));
        secretary.setRoles(userRoles);
        secretary.setPassword(bCryptEncoder.encode(secretary.getPassword()));
        return this.secretaryRepository.save(secretary);
    }

    @Override
    public void deleteSecretary(Secretary secretary){
        secretaryRepository.delete(secretary);
    }

    @Override
    public Secretary findSecretaryById(Integer id) throws NotFoundException {

        Optional<Secretary> secretary = secretaryRepository.findById(id);

        if (secretary.isPresent()) {
            return secretary.get();
        }
        throw new NotFoundException("Secretary with id " + id + " not found");
    }

    @Override
    public Secretary findSecretaryByEmail(String email) throws NotFoundException{

        Optional<Secretary> secretary = secretaryRepository.findByEmail(email);

        if (secretary.isPresent()) {
            return secretary.get();
        }
        throw new NotFoundException("Secretary with email:" + email + " not found");
    }

    @Override
    public Secretary getSecretaryFromToken(String token) throws NotFoundException {
        token = token.replace("Bearer ","");
        return findSecretaryByEmail(this.tokenProvider.getUsernameFromToken(token));
    }

    @Override
    public Iterable<Secretary> getSecretaries() {
        return secretaryRepository.findAll();
    }
}
