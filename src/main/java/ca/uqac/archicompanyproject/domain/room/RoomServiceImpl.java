package ca.uqac.archicompanyproject.domain.room;

import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentRepositoryInterface;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentType;
import ca.uqac.archicompanyproject.domain.equipement.EquipmentTypeRepositoryInterface;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements  RoomService{
    private final RoomRepositoryInterface roomRepository;
    private final EquipmentRepositoryInterface equipmentRepository;
    private final EquipmentTypeRepositoryInterface equipmentTypeRepository;

    @Override
    public Room saveRoom(Room room){
        return roomRepository.save(room);
    }

    @Override
    public Room addEquipment(Integer roomId, Equipment equipment) throws NotFoundException{
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

    public Equipment findEquipmentById(Integer equipmentId) throws NotFoundException{
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

        if (equipment.isPresent()) {
            return equipment.get();
        }
        throw new NotFoundException("Equipment with id " + equipmentId + " not found");
    }

    @Override
    public void deleteEquipment(Equipment equipment){
        //TODO : delete les types ??? Essai ici
        if (((Collection< Equipment>) equipmentRepository.findByEquipmentType(equipment.getEquipmentType())).size() == 1 ){
            equipmentTypeRepository.delete(equipment.getEquipmentType());
        }
        equipmentRepository.delete(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment){
        equipmentTypeRepository.save(equipment.getEquipmentType());
        return equipmentRepository.save(equipment);
    }

    @Override
    public Room findRoomById(Integer id) throws NotFoundException{
        Optional<Room> room = roomRepository.findById(id);

        if (room.isPresent()) {
            return room.get();
        }
        throw new NotFoundException("Room with id " + id + " not found");
    }

    @Override
    public Room findRoomByName(String name) throws NotFoundException{
        name = name.strip();
        Optional<Room> room = roomRepository.findByName(name);

        if (room.isPresent()) {
            return room.get();
        }
        throw new NotFoundException("Room with name " + name + " not found");
    }

    @Override
    public  Iterable<Room> getRooms(){
        return roomRepository.findAll();
    }

    @Override
    public  Iterable<Equipment> getEquipments(){
        return equipmentRepository.findAll();
    }
}
