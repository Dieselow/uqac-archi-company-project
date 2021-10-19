package ca.uqac.archicompanyproject.domain.ConsumableType;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder(builderMethodName = "consumableBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ConsumableType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String name;
    private String brand;
    @OneToMany(targetEntity= Consumable.class,
            mappedBy="consumableType",
            cascade=CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List consumable = new ArrayList<>();
}
