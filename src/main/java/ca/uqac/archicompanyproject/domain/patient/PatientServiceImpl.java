package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
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
    private final CaregiverService caregiverService;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
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
    public Patient getPatientFromToken(String token) throws NotFoundException {
        token = token.replace("Bearer ","");
        return findPatientByEmail(this.tokenProvider.getUsernameFromToken(token));
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


    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public HealthFile findHealthfileById(Integer id) throws NotFoundException {
        Optional<HealthFile> healthFile = healthFileRepository.findById(id);

        if (healthFile.isPresent()) {
            return healthFile.get();
        }
        throw new NotFoundException("HealthFile with id " + id + " not found");
    }

    @Override
    public Patient addHealthFile(Integer patientId, HealthFile healthFile) throws NotFoundException{
        Patient patient = this.findPatientById(patientId);
        healthFile.setPatient(patient);
        patient.setHealthFile(healthFile);
        saveHealthFile(healthFile);
        return patient;
    }

    @Override
    public HealthFile updateHealthFile(Integer patientId, HealthFile healthFile) throws NotFoundException {
        Patient patient = this.findPatientById(patientId);
        healthFile.setPatient(patient);
        return this.saveHealthFile(healthFile);
    }

    @Override
    public HealthFile saveHealthFile(HealthFile healthFile) {
        //Ici on a un probleme si on change a quel patient le healthfile est affecte - si je laisse le update comme actuel, on peut update que en passant par le patient
        healthFileRepository.save(healthFile);
        patientRepository.save(healthFile.getPatient());
        return healthFile;
    }

    @Override
    public void deleteHealthFile(Integer healthFileId) throws NotFoundException {
        HealthFile healthFile = this.findHealthfileById(healthFileId);
        healthFile.getPatient().setHealthFile(null);
        healthFileRepository.delete(healthFile);
    }


    @Override
    public Iterable<Patient> findByPrimaryDoctor(Caregiver caregiver){
        return patientRepository.findByPrimaryDoctor(caregiver);
    }

    @Override
    public boolean checkCaregiverAccessToPatient(String token, Integer patientId) throws NotFoundException {
        Patient patient = this.findPatientById(patientId);
        Caregiver caregiver = caregiverService.getCaregiverFromToken(token);
        return caregiver.getPatients().stream().anyMatch(patientDocteur -> patientDocteur.getID().equals(patient.getID()));
    }
    //Pas de check sur update de l'adresse mail !!!

    @Override
    public boolean checkCaregiverAccessToHealthfile(String token, Integer healthFileId) throws NotFoundException {
        HealthFile healthFile = this.findHealthfileById(healthFileId);
        return this.checkCaregiverAccessToPatient(token, healthFile.getPatient().getID());
    }
}
