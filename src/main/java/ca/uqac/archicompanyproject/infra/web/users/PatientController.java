package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<Patient>> getPatients() {
        try {
            List<Patient> patients = (List<Patient>) patientService.getPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<String> createNewPatient(@RequestBody Patient patient) {
        try {
            patientService.savePatient(patient);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update/:id")
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
        try {
            patientService.savePatient(patient);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/:id")
    public ResponseEntity<String> deletePatient(@RequestParam("id") Integer id) {
        try {
            Patient patient = Patient.builder().ID(id).build();
            patientService.deletePatient(patient);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
