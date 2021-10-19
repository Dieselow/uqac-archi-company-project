package ca.uqac.archicompanyproject.domain.consumable;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumableServiceImpl implements ConsumableService {
    private final ConsumableRepositoryInterface consumableRepository;

    @Override
    public Consumable saveConsumable(Consumable consumable) {
        return this.consumableRepository.save(consumable);
    }

    public Consumable addConsumable(Consumable consumable){
        return this.consumableRepository.save(
                Consumable.consumableBuilder()
                        .quantity(consumable.getQuantity())
                        .threshold(consumable.getThreshold())
                        .consumableType(consumable.getConsumableType())
                        .build()
        );
    }

    public void deleteConsumable(Integer consummableId){
        this.consumableRepository.deleteById(consummableId);
    }

    public Consumable findConsumableById(Integer id) throws NotFoundException{
        Optional<Consumable> consumable;
        consumable = this.consumableRepository.findById(id);
        if (consumable.isPresent()){
            return consumable.get();
        }
        throw new NotFoundException("Consummable with Id : " + id + " not found");
    }



    public Iterable<Consumable> getConsumables(){
        return this.consumableRepository.findAll();
    }

}
