package ca.uqac.archicompanyproject.domain.authentication;

import ca.uqac.archicompanyproject.domain.users.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "UserRights")
@Data
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    private String name;


    @Override
    public String getAuthority() {
        return name;
    }
}
