package ca.uqac.archicompanyproject.infra.web.patient;

import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    //TODO : Ici passer un format passable en sortie
    @GetMapping()
    public ResponseEntity<List<Patient>> getSecretaries() {
        try {
            List<Patient> patients = (List<Patient>) patientService.getPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<String> createNewPatient() {
        try {
            Patient patient = Patient.builder().firstName("John")
                    .lastName("Doe")
                    .password("ALLO").build();
            patientService.savePatient(patient);
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
            Patient patient = patientService.getPatients().iterator().next();
            patient.setFirstName("Pierre");
            patientService.savePatient(patient);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteSecretary(@RequestParam("id") Integer id) {
        try {
            Patient patient = Patient.builder().ID(id).build();
            patientService.deletePatient(patient);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
