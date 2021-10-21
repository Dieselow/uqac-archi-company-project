package ca.uqac.archicompanyproject.domain.consumable;

import ca.uqac.archicompanyproject.domain.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Builder(builderMethodName = "consumableBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer ID;
    Integer quantity;
    Integer threshold;
    @ManyToOne()
    @JoinColumn(name="consumableType_id",
            referencedColumnName = "id")
     ConsumableType consumableType;

    @ManyToMany(mappedBy = "consumables")
    private Set<Ticket> tickets;
}
