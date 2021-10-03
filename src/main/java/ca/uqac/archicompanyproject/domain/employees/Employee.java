package ca.uqac.archicompanyproject.domain.employees;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.users.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Employee extends User {

    private float salary;

    private String workSchedule;

    private Date employmentDate;

    public Employee(Integer ID, String username, String firstName, String lastName, Date dateOfBirth, String email, String password, String address, String phoneNumber, Role role, float salary, String workSchedule, Date employmentDate){
        super(ID, username, firstName, lastName, dateOfBirth, email, password, address, phoneNumber, role);
        this.salary = salary;
        this.workSchedule = workSchedule;
        this.employmentDate = employmentDate;
    }

}
