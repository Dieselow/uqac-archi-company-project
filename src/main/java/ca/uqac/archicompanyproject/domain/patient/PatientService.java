package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.healthfile.HealthFile;
import javassist.NotFoundException;

public interface PatientService {
    Patient savePatient(Patient patient);
    Patient addPatient(Patient patient);
    Patient getPatientFromToken(String token) throws NotFoundException;
    void deletePatient(Patient patient);
    Patient findPatientById(Integer id) throws NotFoundException;
    Patient findPatientByEmail(String email) throws  NotFoundException;
    Iterable<Patient> getPatients();
    Iterable<Patient> findByPrimaryDoctor(Caregiver caregiver);

    HealthFile findHealthfileById(Integer id) throws NotFoundException;
    Patient addHealthFile(Integer patientId, HealthFile healthFile) throws NotFoundException;
    HealthFile updateHealthFile(Integer patientId, HealthFile healthFile) throws NotFoundException;
    HealthFile saveHealthFile(HealthFile healthFile);
    void deleteHealthFile(Integer healthFileId) throws NotFoundException;


}
