package ca.uqac.archicompanyproject.domain.consumable;

import ca.uqac.archicompanyproject.domain.consumableType.ConsumableType;
import javassist.NotFoundException;

public interface ConsumableService {
    Consumable saveConsumable(Consumable consumable);
    Consumable addConsumable(Consumable consumable);
    void deleteConsumable(Consumable consumable);
    Consumable findConsumableById(Integer id) throws NotFoundException;
    Consumable findConsumableByConsumableType(ConsumableType consumableType)throws NotFoundException;

    Iterable<Consumable> getConsumables();
}