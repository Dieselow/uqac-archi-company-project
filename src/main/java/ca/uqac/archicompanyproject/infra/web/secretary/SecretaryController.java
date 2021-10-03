package ca.uqac.archicompanyproject.infra.web.secretary;

import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.domain.secretary.SecretaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees/secretaries")
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
    public ResponseEntity<String> createNewSecretary() {
        try {
            Secretary secretary = Secretary.builder().firstName("Jeanne")
                    .lastName("Dutronc")
                    .salary(1234).password("ALLO").build();
            secretaryService.saveSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateSecretary() {
        try {
            //TODO : modif ca
            Secretary secretary = secretaryService.getSecretaries().iterator().next();
            secretary.setFirstName("Jeannette");
            secretaryService.saveSecretary(secretary);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
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
