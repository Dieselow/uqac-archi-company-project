package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.security.TokenProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepositoryInterface patientRepository;
    private final CaregiverService caregiverService;
    private final PasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;
    private final TokenProvider tokenProvider;

    @Override
    public Patient savePatient(Patient patient) throws NotFoundException {
        if (patient.getPrimaryDoctor() != null){
            patient.setPrimaryDoctor(caregiverService.findCaregiverById(patient.getPrimaryDoctor().getID()));
        }

        return patientRepository.save(patient);
    }

    @Override
    public Patient addPatient(Patient patient) throws NotFoundException {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add( this.roleRepository.findByName(Roles.PATIENT.name()));
        patient.setRoles(userRoles);
        patient.setPassword(bCryptEncoder.encode(patient.getPassword()));

        if (patient.getPrimaryDoctor() != null){
            patient.setPrimaryDoctor(caregiverService.findCaregiverById(patient.getPrimaryDoctor().getID()));
        }
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
    public Iterable<Patient> findByPrimaryDoctor(Caregiver caregiver){
        return patientRepository.findByPrimaryDoctor(caregiver);
    }
}
