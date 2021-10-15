package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.healthfile.HealthFile;
import ca.uqac.archicompanyproject.domain.healthfile.HealthFileRepositoryInterface;
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
public class PatientServiceImpl implements PatientService {

    private final PatientRepositoryInterface patientRepository;
    private final HealthFileRepositoryInterface healthFileRepository;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;
    private final TokenProvider tokenProvider;

    @Override
    public Patient savePatient(Patient patient) {
        saveHealthFile(patient.getHealthFile());

        return patientRepository.save(patient);
    }

    @Override
    public void saveHealthFile(HealthFile healthFile) {
        if(healthFile == null){
            return;
        }

        healthFileRepository.save(healthFile);
    }

    @Override
    public Patient addPatient(Patient patient) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add( this.roleRepository.findByName(Roles.PATIENT.name()));
        patient.setRoles(userRoles);
        patient.setPassword(bCryptEncoder.encode(patient.getPassword()));
        return this.patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientById(Integer id) throws NotFoundException {

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            return patient.get();
        }
        throw new NotFoundException("Patient with id " + id + " not found");
    }

    @Override
    public Patient findPatientByEmail(String email) throws NotFoundException {

        Optional<Patient> patient = patientRepository.findByEmail(email);

        if (patient.isPresent()) {
            return patient.get();
        }
        throw new NotFoundException("Patient with email:" + email + " not found");
    }

    public Patient getPatientFromToken(String token) throws NotFoundException {
        token = token.replace("Bearer ","");
        return findPatientByEmail(this.tokenProvider.getUsernameFromToken(token));
    }

    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public void deleteHealthFile(Patient patient){
        HealthFile healthFile = patient.getHealthFile();
        patient.setHealthFile(null);
        healthFileRepository.delete(healthFile);
    }

}
