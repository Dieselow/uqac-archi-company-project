package ca.uqac.archicompanyproject.domain.ticket;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
}
