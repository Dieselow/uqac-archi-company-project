package ca.uqac.archicompanyproject.domain.consumable;

import ca.uqac.archicompanyproject.domain.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name="consumableType_id",
            referencedColumnName = "id")
     ConsumableType consumableType;

    @ManyToMany(mappedBy = "consumables")
    private Set<Ticket> tickets;
}
