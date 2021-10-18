package ca.uqac.archicompanyproject.domain.consumable;

import javassist.NotFoundException;

public interface ConsumableService {
    Consumable saveConsumable(Consumable consumable);
    Consumable addConsumable(Consumable consumable);
    void deleteConsumable(Consumable consumable);
    Consumable findConsumableById(Integer id) throws NotFoundException;
    Consumable findConsumableByName(String name) throws NotFoundException;

    Iterable<Consumable.ConsumableType> getConsumables();
}
