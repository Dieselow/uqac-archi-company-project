package ca.uqac.archicompanyproject.domain.room;

import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import javassist.NotFoundException;

public interface RoomService {
    Room saveRoom(Room room);
    Room addEquipment(Integer roomId, Equipment equipment) throws NotFoundException;
    Equipment findEquipmentById(Integer equipmentId) throws NotFoundException;
    void deleteEquipment(Equipment equipment);
    Equipment updateEquipment(Equipment equipment);
    Room findRoomById(Integer id) throws NotFoundException;
    Room findRoomByName(String name) throws NotFoundException;
    Iterable<Room> getRooms();
    Iterable<Equipment> getEquipments();
}
