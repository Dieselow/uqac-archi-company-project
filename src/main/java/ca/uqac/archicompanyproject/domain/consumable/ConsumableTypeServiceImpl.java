package ca.uqac.archicompanyproject.domain.consumable;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ConsumableTypeServiceImpl implements ConsumableTypeService {
    //Class repositories
    private final ConsumableTypeRepositoryInterface consumableTypeRepository;

    public ConsumableType saveConsumableType(ConsumableType consumableType){return consumableTypeRepository.save(consumableType);}

    public ConsumableType addConsumableType(ConsumableType consumableType){
        return this.consumableTypeRepository.save(
                ConsumableType.consumableTypeBuilder()
                        .name(consumableType.getName())
                        .brand(consumableType.getBrand())
                        .consumable(consumableType.getConsumable())
                        .build()
        );
    }

    public ConsumableType findConsumableTypeById(Integer id) throws NotFoundException{
        Optional<ConsumableType> consumableType = consumableTypeRepository.findById(id);
        if (consumableType.isPresent()){
            return consumableType.get();
        }
        throw new NotFoundException("ConsumableType id" + id + "not found");
    }

    public ConsumableType findConsumableTypeByName(String name) throws NotFoundException{
        return this.consumableTypeRepository.findByName(name).orElse(null);
    }

    public void deleteConsumableType(ConsumableType consumableType){
        consumableTypeRepository.delete(consumableType);
    }

    public List<ConsumableType> getConsumableTypes(){
        return (List<ConsumableType>) this.consumableTypeRepository.findAll();
    }
}
