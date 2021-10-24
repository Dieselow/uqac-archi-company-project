package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.patient.PatientService;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.domain.secretary.SecretaryService;
import ca.uqac.archicompanyproject.domain.users.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaries")
@AllArgsConstructor
public class SecretaryController {

    private final SecretaryService secretaryService;
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<Secretary>> getSecretaries() {
        try {
            List<Secretary> secretaries = (List<Secretary>) secretaryService.getSecretaries();
            return new ResponseEntity<>(secretaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Secretary> getSecretary(@RequestParam("id") Integer id) {
        try {
            Secretary result = this.secretaryService.findSecretaryById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Ici faudra mettre la sécu
    @PostMapping("/auth/register")
    public ResponseEntity<Secretary> createNewSecretary(@RequestBody Secretary secretary) {
        try {
            if (userService.checkEmailAlreadyExists(secretary)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Secretary result = secretaryService.addSecretary(secretary);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> updateSecretary(@RequestParam("id") Integer id, @RequestBody Secretary secretary) {
        try {
            secretary.setID(id);
            if (userService.checkEmailAlreadyExists(secretary)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            secretaryService.saveSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/details")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Secretary> getCaregiverDetails(@RequestHeader(value = "Authorization") String token) {
        try {
            Secretary secretary = this.secretaryService.getSecretaryFromToken(token);
            return new ResponseEntity<>(secretary, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> deleteSecretary(@RequestParam("id") Integer id) {
        try {
            Secretary secretary = Secretary.builder().ID(id).build();
            secretaryService.deleteSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
