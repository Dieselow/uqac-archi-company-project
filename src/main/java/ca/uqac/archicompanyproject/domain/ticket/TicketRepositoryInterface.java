package ca.uqac.archicompanyproject.domain.ticket;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface TicketRepositoryInterface extends CrudRepository<Ticket,Integer>{
    Optional<Ticket> findByRequestDate(Date requestDate);
}
