package ca.uqac.archicompanyproject.domain.authentication;

import ca.uqac.archicompanyproject.domain.users.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "UserRights")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
