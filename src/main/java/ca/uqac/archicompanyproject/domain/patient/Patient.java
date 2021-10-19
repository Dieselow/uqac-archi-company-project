package ca.uqac.archicompanyproject.domain.patient;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "patient_id")
public class Patient extends User {

    @ManyToOne
    @JsonBackReference
    private Caregiver primaryDoctor;

    @Builder
    public Patient(Integer ID, String username, String firstName, String lastName, Date dateOfBirth, String email, String password, String address, String phoneNumber, Caregiver primaryDoctor, Set<Role> role) {
        super(ID, username, firstName, lastName, dateOfBirth, email, password, address, phoneNumber, role);
        this.primaryDoctor = primaryDoctor;
    }

}