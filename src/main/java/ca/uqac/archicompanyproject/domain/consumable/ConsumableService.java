package ca.uqac.archicompanyproject.domain.consumable;

import javassist.NotFoundException;

public interface ConsumableService {
    Consumable saveConsumable(Consumable consumable);
    Consumable addConsumable(Consumable consumable);
    void deleteConsumable(Integer consummableId);
    Consumable findConsumableById(Integer id) throws NotFoundException;

    Iterable<Consumable> getConsumables();
}
