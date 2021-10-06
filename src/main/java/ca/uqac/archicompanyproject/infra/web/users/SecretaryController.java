package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.domain.secretary.SecretaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaries")
@AllArgsConstructor
public class SecretaryController {

    private final SecretaryService secretaryService;

    @GetMapping()
    public ResponseEntity<List<Secretary>> getSecretaries() {
        try {
            List<Secretary> secretaries = (List<Secretary>) secretaryService.getSecretaries();
            return new ResponseEntity<>(secretaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<String> createNewSecretary(@RequestBody Secretary secretary) {
        try {
            secretaryService.saveSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update/:id")
    public ResponseEntity<String> updateSecretary(@RequestBody Secretary secretary) {
        try {
            secretaryService.saveSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/:id")
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
