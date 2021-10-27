package ca.uqac.archicompanyproject.domain.room;

import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentRepositoryInterface;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentType;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentTypeRepositoryInterface;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements  RoomService {
    private final RoomRepositoryInterface roomRepository;
    private final EquipmentRepositoryInterface equipmentRepository;
    private final EquipmentTypeRepositoryInterface equipmentTypeRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findRoomById(Integer id) throws NotFoundException {
        Optional<Room> room = roomRepository.findById(id);

        if (room.isPresent()) {
            return room.get();
        }
        throw new NotFoundException("Room with id " + id + " not found");
    }

    @Override
    public Room findRoomByName(String name) throws NotFoundException {
        name = name.strip();
        Optional<Room> room = roomRepository.findByName(name);

        if (room.isPresent()) {
            return room.get();
        }
        throw new NotFoundException("Room with name " + name + " not found");
    }

    @Override
    public List<Room> findByNameContaining(String name){
        return (List<Room>) roomRepository.findByNameContaining(name);
    }

    @Override
    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room addEquipment(Integer roomId, Equipment equipment) throws NotFoundException {
        Optional<Room> roomOption = roomRepository.findById(roomId);
        Room room = null;
        if (roomOption.isPresent()) {
            room = roomOption.get();
        } else {
            throw new NotFoundException("Room with id " + roomId + " not found");
        }
        equipment.setRoom(room);
        equipmentTypeRepository.save(equipment.getEquipmentType());
        equipmentRepository.save(equipment);
        return room;
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        equipmentTypeRepository.save(equipment.getEquipmentType());
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Integer equipmentId) throws NotFoundException {
        Equipment equipment = this.findEquipmentById(equipmentId);
        equipmentRepository.delete(equipment);
    }

    @Override
    public Equipment findEquipmentById(Integer equipmentId) throws NotFoundException {
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

        if (equipment.isPresent()) {
            return equipment.get();
        }
        throw new NotFoundException("Equipment with id " + equipmentId + " not found");
    }

    @Override
    public Iterable<Equipment> getEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public EquipmentType addEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeRepository.save(equipmentType);
    }

    @Override
    public EquipmentType updateEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeRepository.save(equipmentType);
    }

    @Override
    public void deleteEquipmentType(Integer equipmentTypeId) {
        EquipmentType equipmentType = EquipmentType.builder().ID(equipmentTypeId).build();
        equipmentTypeRepository.delete(equipmentType);
    }

    @Override
    public EquipmentType getEquipmentTypeById(Integer id) throws NotFoundException {
        Optional<EquipmentType> equipment = equipmentTypeRepository.findById(id);

        if (equipment.isPresent()) {
            return equipment.get();
        }
        throw new NotFoundException("EquipmentType with id " + id + " not found");
    }

    @Override
    public EquipmentType getEquipmentTypeByName(String name) throws NotFoundException{
        Optional<EquipmentType> equipment = equipmentTypeRepository.findByName(name);

        if (equipment.isPresent()) {
            return equipment.get();
        }
        throw new NotFoundException("EquipmentType with name " + name + " not found");
    }

    @Override
    public Iterable<EquipmentType> getEquipmentTypes(){
        return equipmentTypeRepository.findAll();
    }
}
