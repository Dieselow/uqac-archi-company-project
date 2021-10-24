package ca.uqac.archicompanyproject.infra.web.users;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.search.CriteriaParser;
import ca.uqac.archicompanyproject.domain.search.GenericSpecification;
import ca.uqac.archicompanyproject.domain.search.GenericSpecificationsBuilder;
import ca.uqac.archicompanyproject.domain.users.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caregivers")
@AllArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<Caregiver>> getCaregivers(@RequestParam(value = "search", required = false) String search) {
        try {
            if (search == null) {
                List<Caregiver> caregivers = caregiverService.getCaregivers();
                return new ResponseEntity<>(caregivers, HttpStatus.OK);
            }
            List<Caregiver> caregivers = caregiverService.getCaregivers(this.resolveSpecificationFromInfixExpr(search));
            return new ResponseEntity<>(caregivers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Caregiver> getCaregiver(@RequestParam("id") Integer id) {
        try {
            Caregiver result = this.caregiverService.findCaregiverById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Caregiver> createNewCaregiver(@RequestBody Caregiver caregiver) {
        try {
            if (userService.checkEmailAlreadyExists(caregiver)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Caregiver result = caregiverService.addCaregiver(caregiver);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<Caregiver> updateCaregiver(@RequestParam("id") Integer id, @RequestBody Caregiver caregiver) {
        try {
            caregiver.setID(id);
            if (userService.checkEmailAlreadyExists(caregiver)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Caregiver result = caregiverService.saveCaregiver(caregiver);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> deleteCaregiver(@RequestParam("id") Integer id) {
        try {
            Caregiver caregiver = Caregiver.builder().ID(id).build();
            caregiverService.deleteCaregiver(caregiver);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/patients")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<List<Patient>> getCaregiversPatients(@RequestHeader(value = "Authorization") String token) {
        try {
            Caregiver caregiver = this.caregiverService.getCaregiverFromToken(token);
            return new ResponseEntity<>(caregiver.getPatients(), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/view/details")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ResponseEntity<Caregiver> getCaregiverDetails(@RequestHeader(value = "Authorization") String token) {
        try {
            Caregiver patient = this.caregiverService.getCaregiverFromToken(token);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected Specification<Caregiver> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Caregiver> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), GenericSpecification::new);
    }

}
