package ca.uqac.archicompanyproject.infra.web.healthfile;

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
    public ResponseEntity<Patient> createNewHealthFileForPatientId(@RequestParam("id") Integer patientId, @RequestBody HealthFile healthFile) {
        try {
            //TODO : CHECK AUTHO PAS POSSIBLE TANT QUE PRIMARY PAS IMPLEMENTE
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
    public ResponseEntity<HealthFile> updateHealthFileForPatientId(@RequestParam("id") Integer patientId, @RequestBody HealthFile healthFile) {
        try {
            //TODO : CHECK AUTHO PAS POSSIBLE TANT QUE PRIMARY PAS IMPLEMENTE
            patientService.updateHealthFile(patientId, healthFile);
            return new ResponseEntity<>(healthFile, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<HealthFile> viewHealthFileFromPatientId(@RequestParam("id") Integer healthFileId) {
        try {
            //TODO : CHECK AUTHO PAS POSSIBLE TANT QUE PRIMARY PAS IMPLEMENTE
            HealthFile healthFile = patientService.findHealthfileById(healthFileId);
            return new ResponseEntity<>(healthFile, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<HealthFile> deleteHealthFileFromPatientId(@RequestParam("id") Integer healthFileId) {
        try {
            //TODO : CHECK AUTHO PAS POSSIBLE TANT QUE PRIMARY PAS IMPLEMENTE
            patientService.deleteHealthFile(healthFileId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
