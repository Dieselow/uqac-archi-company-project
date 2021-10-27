package ca.uqac.archicompanyproject.domain.ticket;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Builder(builderMethodName = "ticketBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer ID;
    Date requestDate;
    @ManyToMany
    @JoinTable(
            name = "ticket_consumables",
            joinColumns = @JoinColumn (name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "consumable_id")
    )
    Set<Consumable> consumables;
}
