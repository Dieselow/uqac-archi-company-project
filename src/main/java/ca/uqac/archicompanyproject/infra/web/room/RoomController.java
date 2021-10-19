package ca.uqac.archicompanyproject.infra.web.room;

import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentType;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.room.Room;
import ca.uqac.archicompanyproject.domain.room.RoomService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping()
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<Room>> getRooms() {
        try {
            List<Room> rooms = (List<Room>) roomService.getRooms();
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/equipments")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<List<Equipment>> getEquipments() {
        try {
            List<Equipment> equipments = (List<Equipment>) roomService.getEquipments();
            return new ResponseEntity<>(equipments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/equipments/add/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Room> addEquipment(@RequestParam("id") Integer roomId, @RequestBody() Equipment equipment) {
        try {
            Room room = roomService.addEquipment(roomId, equipment);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/equipments/delete/:id")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<String> deleteEquipment(@RequestParam("id") Integer equipmentId) {
        try {
            roomService.deleteEquipment(Equipment.builder().ID(equipmentId).build());
            return new ResponseEntity<>("Success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/equipments/update")
    @PreAuthorize("hasRole('SECRETARY')")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody() Equipment equipment) {
        try {
            equipment = roomService.updateEquipment(equipment);
            return new ResponseEntity<>(equipment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
