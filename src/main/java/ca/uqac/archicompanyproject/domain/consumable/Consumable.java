package ca.uqac.archicompanyproject.domain.consumable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder(builderMethodName = "consumableBuilder")
@AllArgsConstructor
@NoArgsConstructor

public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private Integer quantity;
    private Integer threshold;

    @Entity
    @Data
    public static class ConsumableType {
        private String name;
        private String brand;
    }

}
