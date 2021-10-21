package ca.uqac.archicompanyproject.domain.caregiver;

import javassist.NotFoundException;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CaregiverService {
    Caregiver saveCaregiver(Caregiver caregiver);
    Caregiver addCaregiver(Caregiver caregiver);
    void deleteCaregiver(Caregiver caregiver);
    Caregiver findCaregiverById(Integer id) throws NotFoundException;
    Caregiver findCaregiverByEmail(String email) throws  NotFoundException;
    Caregiver getCaregiverFromToken(String token) throws NotFoundException;
    List<Caregiver> getCaregivers(Specification specification);
    List<Caregiver> getCaregivers();
}
