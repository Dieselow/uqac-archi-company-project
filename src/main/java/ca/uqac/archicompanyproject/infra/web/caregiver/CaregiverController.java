package ca.uqac.archicompanyproject.infra.web.caregiver;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees/caregivers")
@AllArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    //TODO : Ici passer un format passable en sortie
    @GetMapping()
    public ResponseEntity<List<Caregiver>> getSecretaries() {
        try {
            List<Caregiver> caregivers = (List<Caregiver>) caregiverService.getCaregivers();
            return new ResponseEntity<>(caregivers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<String> createNewPatient() {
        try {
            Caregiver caregiver = Caregiver.builder().firstName("John")
                    .lastName("Doe")
                    .password("ALLO").build();
            caregiverService.saveCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO : Ici passer l'objet en création
    @GetMapping("/update")
    public ResponseEntity<String> updateSecretary() {
        try {
            //TODO : modif ca
            Caregiver caregiver = caregiverService.getCaregivers().iterator().next();
            caregiver.setLicenceNumber("0");
            caregiverService.saveCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteSecretary(@RequestParam("id") Integer id) {
        try {
            Caregiver caregiver = Caregiver.builder().ID(id).build();
            caregiverService.deleteCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
