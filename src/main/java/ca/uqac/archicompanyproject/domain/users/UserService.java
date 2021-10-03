package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import javassist.NotFoundException;

import java.util.Optional;

public interface UserService {
    //Secretary CRUD
    Secretary saveSecretary(Secretary secretary);
    void deleteSecretary(Secretary secretary);
    Secretary findSecretaryById(Integer id);
    Secretary findSecretaryByEmail(String email);
    Iterable<Secretary> getSecretaries();

    //Caregiver CRUD
    Caregiver saveCaregiver(Caregiver caregiver);
    void deleteCaregiver(Caregiver caregiver);
    Caregiver findCaregiverById(Integer id);
    Caregiver findCaregiverByEmail(String email);
    Iterable<Caregiver> getCaregivers();

    //Patient CRUD
    Patient savePatient(Patient patient);
    void deletePatient(Patient patient);
    Patient findPatientById(Integer id);
    Patient findPatientByEmail(String email);
    Iterable<Patient> getPatients();

    //Employee
    Employee findEmployeeById(Integer id);

    //CRUD USER ?? A DELETE
    User addUser(User user);
    User findUserByEmail(String email) throws NotFoundException;
    User findUserById(Integer id) throws NotFoundException;
    Iterable<User> getUsers();
}
