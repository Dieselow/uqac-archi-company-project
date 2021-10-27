package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserService;
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
    private final UserService userService;
    private final CaregiverService caregiverService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<List<Patient>> getPatients(@RequestHeader(value = "Authorization") String token) {
        try {
            //Essai authentification médecin
            Caregiver caregiver = caregiverService.getCaregiverFromToken(token);
            List<Patient> patients = caregiver.getPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (NotFoundException e){
            //Sinon on est secretaire
            List<Patient> patients = (List<Patient>) patientService.getPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Patient> createNewPatient(@RequestBody Patient patient) {
        try {
            if (userService.checkEmailAlreadyExists(patient)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Patient result = patientService.addPatient(patient);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<Patient> getPatient(@RequestHeader(value="Authorization") String token,@RequestParam("id") Integer id) {
        try {
            //Essai authentification médecin
            Caregiver caregiver = caregiverService.getCaregiverFromToken(token);
            Patient patient = patientService.findPatientById(id);
            if (patient.getPrimaryDoctor() != null && patient.getPrimaryDoctor().getID().equals(caregiver.getID())){
                return new ResponseEntity<>(patient, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NotFoundException e){
            //Sinon on est secretaire
            try {
                Patient patient = patientService.findPatientById(id);
                return new ResponseEntity<>(patient, HttpStatus.OK);
            } catch (NotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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

            if (userService.checkEmailAlreadyExists(patient)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            patient.setID(id);
            Patient result = patientService.savePatient(patient);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            if (userService.checkEmailAlreadyExists(patient)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Patient result = patientService.savePatient(patient);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> deletePatient(@RequestParam("id") Integer patientId) {
        try {
            patientService.deletePatient(patientId);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/primaryDoctor/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Caregiver> getPrimaryDoctorFromPatientId(@RequestParam("id") Integer id) {
        try {
            Patient patient = this.patientService.findPatientById(id);
            Caregiver caregiver = patient.getPrimaryDoctor();
            return new ResponseEntity<>(caregiver, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
