package ca.uqac.archicompanyproject.domain.ticket;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);
    Ticket addTicket(Ticket ticket);
    Ticket findTicketById(Integer id) throws NotFoundException;
    Ticket findTicketByDate(Date date) throws NotFoundException;
    void deleteTicket(Ticket ticket);
    List<Ticket> getTickets();
}
