package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Builder(builderMethodName = "userBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    private String username;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

    @Type(type = "text")
    private String password;

    private String address;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}