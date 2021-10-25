package ca.uqac.archicompanyproject.domain.caregiver;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import ca.uqac.archicompanyproject.domain.consts.RootConsts;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "caregiver_id")
public class Caregiver extends Employee {

    String licenceNumber;

    @OneToMany(mappedBy = "primaryDoctor")
    List<Patient> patients;

    @Builder
    public Caregiver(Integer ID, String username, String firstName, String lastName, Date dateOfBirth, String email, String password, String address, String phoneNumber, Set<Role> role, float salary, String workSchedule, Date employmentDate, String licenceNumber) {
        super(ID, username, firstName, lastName, dateOfBirth, email, password, address, phoneNumber, role, salary, workSchedule, employmentDate);
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String getRoot(){
        return RootConsts.ROOT_VIEW_FRONT_CAREGIVER + getID().toString() + "&root=" + RootConsts.ROOT_API + RootConsts.ROOT_VIEW_CAREGIVER + getID().toString();
    }

}
