package ca.uqac.archicompanyproject.domain.patient;

import javassist.NotFoundException;

public interface PatientService {
    Patient savePatient(Patient patient);
    void deletePatient(Patient patient);
    Patient findPatientById(Integer id) throws NotFoundException;
    Patient findPatientByEmail(String email) throws  NotFoundException;
    Iterable<Patient> getPatients();
}
