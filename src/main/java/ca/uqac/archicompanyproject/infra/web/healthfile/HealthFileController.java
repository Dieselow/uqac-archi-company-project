package ca.uqac.archicompanyproject.infra.web.healthfile;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.healthfile.HealthFile;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthfile")
@AllArgsConstructor
public class HealthFileController {

    private final PatientService patientService;

    @PostMapping("/create/:id")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<Patient> createNewHealthFileForPatientId(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Integer patientId, @RequestBody HealthFile healthFile) {
        try {
            if (!patientService.checkCaregiverAccessToPatient(token, patientId)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Patient patient = patientService.addHealthFile(patientId, healthFile);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<HealthFile> updateHealthFileForPatientId(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Integer patientId, @RequestBody HealthFile healthFile) {
        try {
            if (!patientService.checkCaregiverAccessToPatient(token, patientId)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            patientService.updateHealthFile(patientId, healthFile);
            return new ResponseEntity<>(healthFile, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasAnyRole('CAREGIVER','PATIENT')")
    public ResponseEntity<HealthFile> viewHealthFileFromPatientId(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Integer healthFileId) {
        try {
            //Check si authentification patient
            Patient currentPatient = this.patientService.getPatientFromToken(token);
            if (currentPatient.getHealthFile() == null || currentPatient.getHealthFile().getID() != healthFileId) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            HealthFile healthFile = patientService.findHealthfileById(healthFileId);
            return new ResponseEntity<>(healthFile, HttpStatus.OK);
        } catch (NotFoundException e) {
            try {
                //Authentification médecin
                if (!patientService.checkCaregiverAccessToHealthfile(token, healthFileId)) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                HealthFile healthFile = patientService.findHealthfileById(healthFileId);
                return new ResponseEntity<>(healthFile, HttpStatus.OK);
            } catch (NotFoundException ex){
                //vraiment pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<HealthFile> deleteHealthFileFromPatientId(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Integer healthFileId) {
        try {
            if (!patientService.checkCaregiverAccessToHealthfile(token, healthFileId)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            patientService.deleteHealthFile(healthFileId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
