package ca.uqac.archicompanyproject.domain.consumableType;

import javassist.NotFoundException;

import java.util.*;
public interface ConsumableTypeService {
    ConsumableType saveConsumableType(ConsumableType consumableType);
    ConsumableType addConsumableType(ConsumableType consumableType);
    ConsumableType findConsumableTypeById(Integer id) throws NotFoundException;
    ConsumableType findConsumableTypeByName(String name) throws NotFoundException;
    void deleteConsumableType(ConsumableType consumableType);
    List<ConsumableType> getConsumableTypes();

}
