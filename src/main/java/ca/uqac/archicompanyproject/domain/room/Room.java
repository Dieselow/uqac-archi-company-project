package ca.uqac.archicompanyproject.domain.room;

import ca.uqac.archicompanyproject.domain.consts.RootConsts;
import ca.uqac.archicompanyproject.domain.equipement.Equipment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    String name;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "room")
    @JsonManagedReference
    List<Equipment> equipments = new ArrayList<Equipment>();

    public String getRoot(){
        return RootConsts.ROOT_API + RootConsts.ROOT_VIEW_ROOM + getID();
    }
}
