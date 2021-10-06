package ca.uqac.archicompanyproject.domain.secretary;

import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SecretaryServiceImpl implements  SecretaryService{

    private final SecretaryRepositoryInterface secretaryRepository;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        return secretaryRepository.save(secretary);
    }

    @Override
    public Secretary addSecretary(Secretary secretary) {
        secretary.setRole(this.roleRepository.findByName(Roles.SECRETARY.toString()));
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
    public Iterable<Secretary> getSecretaries() {
        return secretaryRepository.findAll();
    }
}
