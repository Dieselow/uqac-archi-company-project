package ca.uqac.archicompanyproject.domain.room;

import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentType;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import javassist.NotFoundException;

public interface RoomService {
    Room saveRoom(Room room);
    Room findRoomById(Integer id) throws NotFoundException;
    Room findRoomByName(String name) throws NotFoundException;
    Iterable<Room> getRooms();

    Room addEquipment(Integer roomId, Equipment equipment) throws NotFoundException;
    Equipment updateEquipment(Equipment equipment);
    void deleteEquipment(Integer equipmentId) throws NotFoundException;
    Equipment findEquipmentById(Integer equipmentId) throws NotFoundException;
    Iterable<Equipment> getEquipments();

    EquipmentType addEquipmentType(EquipmentType equipmentType);
    EquipmentType updateEquipmentType(EquipmentType equipmentType);
    void deleteEquipmentType(Integer equipmentTypeId);
    EquipmentType getEquipmentTypeById(Integer id) throws NotFoundException;
    EquipmentType getEquipmentTypeByName(String name) throws NotFoundException;
    Iterable<EquipmentType> getEquipmentTypes();
}
