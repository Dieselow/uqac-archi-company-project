package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.authentication.RoleRepository;
import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverRepositoryInterface;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientRepositoryInterface;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.domain.secretary.SecretaryRepositoryInterface;
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

    //Class repositories
    private final UserRepositoryInterface userRepository;
    private final SecretaryRepositoryInterface secretaryRepository;
    private final CaregiverRepositoryInterface caregiverRepository;
    private final PatientRepositoryInterface patientRepository;

    //Other
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptEncoder;

    public UserServiceImpl(UserRepositoryInterface userRepository, SecretaryRepositoryInterface secretaryRepository, CaregiverRepositoryInterface caregiverRepository, PatientRepositoryInterface patientRepository, RoleRepository roleRepository, PasswordEncoder bCryptEncoder) {
        this.userRepository = userRepository;
        this.secretaryRepository = secretaryRepository;
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    ///Secretary CRUD

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        //Doit juste faire attention a ne pas réencoder un password
        if (findSecretaryById(secretary.getID()) == null){
            secretary.setPassword(bCryptEncoder.encode(secretary.getPassword()));
        }
        return secretaryRepository.save(secretary);
    }

    @Override
    public void deleteSecretary(Secretary secretary){
        secretaryRepository.delete(secretary);
    }

    @Override
    public Secretary findSecretaryById(Integer id){
        return secretaryRepository.findById(id).orElse(null);
    }

    @Override
    public Secretary findSecretaryByEmail(String email){
        return secretaryRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Iterable<Secretary> getSecretaries() {
        return secretaryRepository.findAll();
    }

    @Override
    public Caregiver saveCaregiver(Caregiver caregiver){
        //Doit juste faire attention a ne pas réencoder un password
        if (findCaregiverById(caregiver.getID()) == null){
            caregiver.setPassword(bCryptEncoder.encode(caregiver.getPassword()));
        }
        return caregiverRepository.save(caregiver);
    }

    @Override
    public void deleteCaregiver(Caregiver caregiver){
        caregiverRepository.delete(caregiver);
    }

    @Override
    public Caregiver findCaregiverById(Integer id){
        return caregiverRepository.findById(id).orElse(null);
    }

    @Override
    public Caregiver findCaregiverByEmail(String email){
        return caregiverRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Iterable<Caregiver> getCaregivers() {
        return caregiverRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
        //Doit juste faire attention a ne pas réencoder un password
        if (findPatientById(patient.getID()) == null){
            patient.setPassword(bCryptEncoder.encode(patient.getPassword()));
        }
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Patient patient){
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientById(Integer id){
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient findPatientByEmail(String email){
        return patientRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Employee findEmployeeById(Integer id){
        Employee employee;
        employee = findSecretaryById(id);
        if (employee!= null) {
            return employee;
        } else {
            employee = findCaregiverById(id);
            if(employee != null){
                return employee;
            }
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        if (user.getRole() == null) {
            user.setRole(this.roleRepository.findByName(Roles.USER.toString()));
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
                        .role(user.getRole())
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
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }




    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
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
