package ca.uqac.archicompanyproject.domain.consumable;

import ca.uqac.archicompanyproject.domain.consumableType.ConsumableType;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumableServiceImpl implements ConsumableService {
    //Class repositories
    private final ConsumableRepositoryInterface consumableRepository;

    @Override
    public Consumable saveConsumable(Consumable consumable) {
        return this.consumableRepository.save(consumable);
    }

    @Override
    public Consumable addConsumable(Consumable consumable){
        return this.consumableRepository.save(
                Consumable.consumableBuilder()
                        .quantity(consumable.getQuantity())
                        .threshold(consumable.getThreshold())
                        .consumableType(consumable.getConsumableType())
                        .build()
        );
    }
    @Override
    public void deleteConsumable(Consumable consumable){
        this.consumableRepository.delete(consumable);
    }

    @Override
    public Consumable findConsumableById(Integer id) throws NotFoundException{
        Optional<Consumable> consumable;
        consumable = this.consumableRepository.findById(id);
        if (consumable.isPresent()){
            return consumable.get();
        }
        throw new NotFoundException("Consumable with Id : " + id + " not found");
    }

    @Override
    public Consumable findConsumableByConsumableType(ConsumableType consumableType) throws NotFoundException{
        return this.consumableRepository.findByConsumableType(consumableType).orElse(null);
    }

    @Override
    public Iterable<Consumable> getConsumables(){
        return this.consumableRepository.findAll();
    }

}
