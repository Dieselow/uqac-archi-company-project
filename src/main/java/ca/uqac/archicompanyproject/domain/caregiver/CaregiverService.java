package ca.uqac.archicompanyproject.domain.caregiver;

import javassist.NotFoundException;

public interface CaregiverService {
    Caregiver saveCaregiver(Caregiver caregiver);
    Caregiver addCaregiver(Caregiver caregiver);
    void deleteCaregiver(Caregiver caregiver);
    Caregiver findCaregiverById(Integer id) throws NotFoundException;
    Caregiver findCaregiverByEmail(String email) throws  NotFoundException;
    Iterable<Caregiver> getCaregivers();
}
