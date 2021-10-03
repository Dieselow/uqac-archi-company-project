package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepositoryInterface patientRepository;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Patient savePatient(Patient patient) {
        //Doit juste faire attention a ne pas réencoder un password
        if (patient.getID() == null){
            patient.setPassword(bCryptEncoder.encode(patient.getPassword()));
            patient.setRole(this.roleRepository.findByName(Roles.USER.toString()));
        }
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Patient patient){
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientById(Integer id) throws NotFoundException{

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            return patient.get();
        }
        throw new NotFoundException("Patient with id " + id + " not found");
    }

    @Override
    public Patient findPatientByEmail(String email) throws  NotFoundException {

        Optional<Patient> patient = patientRepository.findByEmail(email);

        if (patient.isPresent()) {
            return patient.get();
        }
        throw new NotFoundException("Patient with email:" + email + " not found");
    }

    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
