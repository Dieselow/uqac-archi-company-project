package ca.uqac.archicompanyproject.domain.consumableType;

import ca.uqac.archicompanyproject.domain.consumable.Consumable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder(builderMethodName = "consumableTypeBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ConsumableType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer ID;
    String name;
    String brand;
    @OneToMany(targetEntity= Consumable.class,
            mappedBy="consumableType",
            cascade=CascadeType.ALL,
            fetch = FetchType.LAZY)
    List consumable = new ArrayList<>();
}
