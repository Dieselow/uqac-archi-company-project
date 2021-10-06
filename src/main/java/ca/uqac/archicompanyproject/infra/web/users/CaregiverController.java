package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.users.User;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caregivers")
@AllArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    //TODO : Ici passer un format passable en sortie
    @GetMapping()
    public ResponseEntity<List<Caregiver>> getCaregivers() {
        try {
            List<Caregiver> caregivers = (List<Caregiver>) caregiverService.getCaregivers();
            return new ResponseEntity<>(caregivers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> createNewCaregiver(@RequestBody Caregiver caregiver) {
        try {
            this.caregiverService.findCaregiverByEmail(caregiver.getEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException notFoundException) {
            caregiverService.addCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //LOUIS ici en fait create et update font la meme chose, donc refacto ? A voir juste si les routes doivent etre differentes ?
    //Les histoires de routes c'est pas mal nouveau pour moi déso
    @PutMapping("/update/:id")
    public ResponseEntity<String> updateCaregiver(@RequestParam("id") Integer id, @RequestBody Caregiver caregiver) {
        try {
            caregiverService.saveCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/:id")
    public ResponseEntity<String> deleteCaregiver(@RequestParam("id") Integer id) {
        try {
            Caregiver caregiver = Caregiver.builder().ID(id).build();
            caregiverService.deleteCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
