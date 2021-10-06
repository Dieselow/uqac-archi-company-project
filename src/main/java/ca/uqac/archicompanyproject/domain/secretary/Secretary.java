package ca.uqac.archicompanyproject.domain.secretary;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "secretary_id")
public class Secretary extends Employee {
    @Builder
    public Secretary(Integer ID, String username, String firstName, String lastName, Date dateOfBirth, String email, String password, String address, String phoneNumber, Role role, float salary, String workSchedule, Date employmentDate) {
        super(ID, username, firstName, lastName, dateOfBirth, email, password, address, phoneNumber, role, salary, workSchedule, employmentDate);
    }
}
