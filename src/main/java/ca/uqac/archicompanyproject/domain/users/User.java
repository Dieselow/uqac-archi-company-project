package ca.uqac.archicompanyproject.domain.users;

import ca.uqac.archicompanyproject.domain.authentication.Role;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id") })
    private Set<Role> roles;

    public String getRoot(){return null;}

    @Override
    public boolean equals(Object o){
        if (o instanceof User){
            return this.getID().equals(((User) o).getID());
        }
       return false;
    }
}