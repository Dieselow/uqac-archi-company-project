package ca.uqac.archicompanyproject.domain.ticket;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
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
    private Integer ID;
    private Date requestDate;
    @ManyToMany
    @JoinTable(
            name = "ticket_consumables",
            joinColumns = @JoinColumn (name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "consumable_id")
    )
    private Set<Consumable> consumables;
}
