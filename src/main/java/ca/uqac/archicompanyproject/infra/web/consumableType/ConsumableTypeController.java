package ca.uqac.archicompanyproject.infra.web.consumableType;

import ca.uqac.archicompanyproject.domain.consumableType.ConsumableType;
import ca.uqac.archicompanyproject.domain.consumableType.ConsumableTypeService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumableType")
public class ConsumableTypeController {

    private final ConsumableTypeService consumableTypeService;

    public ConsumableTypeController(ConsumableTypeService consumableTypeService) {this.consumableTypeService = consumableTypeService; }

    @GetMapping()
    @PreAuthorize("hasAnyRole('SECRETARY', 'CAREGIVER')")
    public ResponseEntity<List<ConsumableType>> getConsumableTypes() {
        try {
            List<ConsumableType> consumableTypes = consumableTypeService.getConsumableTypes();
            return new ResponseEntity<>(consumableTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasAnyRole('CAREGIVER','SECRETARY')")
    public ResponseEntity<ConsumableType> getConsumableType(@RequestParam("id") Integer id) {
        try {
            ConsumableType result = this.consumableTypeService.findConsumableTypeById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<ConsumableType> updateConsumableType(@RequestParam("id") Integer id, @RequestBody ConsumableType consumableType) {
        try {
            ConsumableType result = consumableTypeService.saveConsumableType(consumableType);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<ConsumableType> createNewConsumableType(@RequestBody ConsumableType consumableType) {
        try {
            this.consumableTypeService.findConsumableTypeByName(consumableType.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException exception) {
            ConsumableType result = consumableTypeService.addConsumableType(consumableType);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> deleteConsumableType(@RequestParam("id") Integer id) {
        try {
            ConsumableType consumableType = ConsumableType.consumableTypeBuilder().ID(id).build();
            consumableTypeService.deleteConsumableType(consumableType);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
