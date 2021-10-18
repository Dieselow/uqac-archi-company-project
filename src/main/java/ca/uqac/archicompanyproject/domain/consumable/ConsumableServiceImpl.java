package ca.uqac.archicompanyproject.domain.consumable;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumableServiceImpl implements ConsumableService {
    private final ConsumableRepositoryInterface consumableRepository;

    Consumable addConsumable(Consumable consumable){
        return this.consumableRepository.save(
                Consumable.consumableBuilder()
                        .quantity(consumable.getQuantity())
                        .threshold(consumable.getThreshold())
                        .name(consumable.consumableType.getName()) //NON FONCTIONNEL
                        .brand(consumable.consumableType.getBrand()) // NON FONCTIONNEL
                        .build()
        );
    }

    void deleteConsumable(Consumable consumable){}

    Consumable findConsumableById(Integer id) throws NotFoundException{
        ;
    }

    Consumable findConsumableByName(String name) throws NotFoundException{

    }



    public Iterable<Consumable.ConsumableType> getConsumables(){
        return this.consumableRepository.findAll();
    }

}
