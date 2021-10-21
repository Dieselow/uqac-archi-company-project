package ca.uqac.archicompanyproject.infra.web.consumable;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
import ca.uqac.archicompanyproject.domain.consumable.ConsumableService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumable")
public class ConsumableController {
    private final ConsumableService consumableService;

    public ConsumableController(ConsumableService consumableService) {this.consumableService = consumableService; }

    @GetMapping()
    @PreAuthorize("hasAnyRole('SECRETARY', 'CAREGIVER')")
    public ResponseEntity<List<Consumable>> getConsumables() {
        try {
            List<Consumable> consumables = (List<Consumable>) consumableService.getConsumables();
            return new ResponseEntity<>(consumables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasAnyRole('CAREGIVER','SECRETARY')")
    public ResponseEntity<Consumable> getConsumable(@RequestParam("id") Integer id) {
        try {
            Consumable result = this.consumableService.findConsumableById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<Consumable> updateConsumable(@RequestParam("id") Integer id, @RequestBody Consumable consumable) {
        try {
            Consumable result = consumableService.saveConsumable(consumable);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/:id")
    @PreAuthorize("hasRole('CAREGIVER, SECRETARY')")
    public ResponseEntity<Consumable> createNewConsumable(@RequestBody Consumable consumable) {
        try {
            this.consumableService.findConsumableByConsumableType(consumable.getConsumableType());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException exception) {
            Consumable result = consumableService.addConsumable(consumable);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('CAREGIVER, SECRETARY')")
    public ResponseEntity<String> deleteConsumable(@RequestParam("id") Integer id) {
        try {
            Consumable consumable = Consumable.consumableBuilder().ID(id).build();
            consumableService.deleteConsumable(consumable);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
