package ca.uqac.archicompanyproject.domain.caregiver;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.users.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
//@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "caregiver_id")
public class Caregiver extends Employee {

    private String licenceNumber;

    @Builder
    public Caregiver(Integer ID, String username, String firstName, String lastName, Date dateOfBirth, String email, String password, String address, String phoneNumber, Role role, float salary, String workSchedule, Date employmentDate, String licenceNumber){
        super(ID, username, firstName, lastName, dateOfBirth, email, password, address, phoneNumber, role, salary, workSchedule, employmentDate);
        this.licenceNumber = licenceNumber;
    }

}
