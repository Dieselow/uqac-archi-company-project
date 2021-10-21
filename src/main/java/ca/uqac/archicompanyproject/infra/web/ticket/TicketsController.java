package ca.uqac.archicompanyproject.infra.web.ticket;

import ca.uqac.archicompanyproject.domain.ticket.Ticket;
import ca.uqac.archicompanyproject.domain.ticket.TicketService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketsController {

    private final TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //Get tous les tickets
    @GetMapping()
    @PreAuthorize("hasAnyRole('CAREGIVER','SECRETARY')")
    public ResponseEntity<List<Ticket>> getTickets() {
        try {
            List<Ticket> tickets = ticketService.getTickets();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/:id")
    @PreAuthorize("hasAnyRole('CAREGIVER','SECRETARY')")
    public ResponseEntity<Ticket> getTicket(@RequestParam("id") Integer id) {
        try {
            Ticket result = this.ticketService.findTicketById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/:id")
    @PreAuthorize("hasAnyRole('SECRETARY','CAREGIVER')")
    public ResponseEntity<Ticket> updateTicket(@RequestParam("id") Integer id, @RequestBody Ticket ticket) {
        try {
            Ticket result = ticketService.saveTicket(ticket);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //créer un nouveau ticket
    @PostMapping("/update/:id")
    @PreAuthorize("hasRole('CAREGIVER, SECRETARY')")
    public ResponseEntity<Ticket> createNewTicket(@RequestBody Ticket ticket) {
        try {
            this.ticketService.findTicketByDate(ticket.getRequestDate());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException exception) {
            Ticket result = ticketService.addTicket(ticket);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //supprimer un ticket
    @DeleteMapping("/delete/:id")
    @PreAuthorize("hasRole('CAREGIVER, SECRETARY')")
    public ResponseEntity<String> deleteTicket(@RequestParam("id") Integer id) {
        try {
            Ticket ticket = Ticket.ticketBuilder().ID(id).build();
            ticketService.deleteTicket(ticket);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}