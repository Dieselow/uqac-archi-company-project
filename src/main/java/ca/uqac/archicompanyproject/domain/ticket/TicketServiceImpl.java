package ca.uqac.archicompanyproject.domain.ticket;

//import ca.uqac.archicompanyproject.domain.consumable.Consumable

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TicketServiceImpl {
    //Class repositories
    private final TicketRepositoryInterface ticketRepository;

    //Other
    //private final ConsumableTypeRepositoryInterface consumableTypeRepository;

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket addTicket(Ticket ticket){
        return this.ticketRepository.save(
                Ticket.ticketBuilder()
                        .requestDate(ticket.getRequestDate())
                        .build()
        );
    }

    public Ticket findTicketById(Integer id) throws NotFoundException {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        }
        throw new NotFoundException("Ticket with id" + id + "not found");
    }

    public Ticket findTicketByDate(Date requestDate) throws NotFoundException{
        return this.ticketRepository.findByRequestDate(requestDate).orElse(null);
    }

    public void deleteTicket(Ticket ticket){
        ticketRepository.delete(ticket);
    }

    public List<Ticket> getTickets() {
        return (List<Ticket>) this.ticketRepository.findAll();
    }

}
