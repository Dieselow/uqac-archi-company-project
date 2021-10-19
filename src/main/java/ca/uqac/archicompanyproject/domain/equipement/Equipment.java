package ca.uqac.archicompanyproject.domain.equipement;

import ca.uqac.archicompanyproject.domain.room.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    private Date installationDate;

    @OneToOne
    @JoinColumn(name = "equipmentTypeId", referencedColumnName = "id")
    private EquipmentType equipmentType;

    @ManyToOne
    @JsonBackReference
    private Room room;
}
