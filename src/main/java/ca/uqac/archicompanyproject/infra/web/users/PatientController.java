package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping()
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<Patient>> getPatients() {
        try {
            List<Patient> patients = (List<Patient>) patientService.getPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Patient> createNewPatient(@RequestBody Patient patient) {
        try {
            this.patientService.findPatientByEmail(patient.getEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException notFoundException) {
            try {
                Patient result = patientService.addPatient(patient);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (NotFoundException notFoundException1) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Patient> getPatient(@RequestParam("id") Integer id) {
        try {
            Patient result = this.patientService.findPatientById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/details")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient> getPatientDetails(@RequestHeader(value = "Authorization") String token) {
        try {
            Patient patient = this.patientService.getPatientFromToken(token);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasAnyRole('PATIENT','SECRETARY')")
    public ResponseEntity<Patient> updatePatient(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Integer id, @RequestBody Patient patient) {
        try {
            Patient currentPatient = this.patientService.getPatientFromToken(token);
            if (currentPatient.getID() != id) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Patient result = patientService.savePatient(patient);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            try {
                Patient result = patientService.savePatient(patient);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (NotFoundException notFoundException){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
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
