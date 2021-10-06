package ca.uqac.archicompanyproject.domain.caregiver;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CaregiverServiceImpl implements  CaregiverService{

    private final CaregiverRepositoryInterface caregiverRepository;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Caregiver saveCaregiver(Caregiver caregiver){
        return caregiverRepository.save(caregiver);
    }

    @Override
    public Caregiver addCaregiver(Caregiver caregiver) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add( this.roleRepository.findByName(Roles.CAREGIVER.name()));
        caregiver.setRoles(userRoles);
        caregiver.setPassword(bCryptEncoder.encode(caregiver.getPassword()));
        return this.caregiverRepository.save(caregiver);
    }

    @Override
    public void deleteCaregiver(Caregiver caregiver){
        caregiverRepository.delete(caregiver);
    }

    @Override
    public Caregiver findCaregiverById(Integer id) throws NotFoundException{
        Optional<Caregiver> caregiver = caregiverRepository.findById(id);

        if (caregiver.isPresent()) {
            return caregiver.get();
        }
        throw new NotFoundException("Caregiver with id " + id + " not found");
    }

    @Override
    public Caregiver findCaregiverByEmail(String email) throws NotFoundException{
        Optional<Caregiver> caregiver = caregiverRepository.findByEmail(email);

        if (caregiver.isPresent()) {
            return caregiver.get();
        }
        throw new NotFoundException("Caregiver with email:" + email + " not found");
    }

    @Override
    public Iterable<Caregiver> getCaregivers() {
        return caregiverRepository.findAll();
    }
}
