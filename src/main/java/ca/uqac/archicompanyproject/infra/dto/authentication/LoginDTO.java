package ca.uqac.archicompanyproject.infra.dto.authentication;


import lombok.Data;

@Data
public class LoginDTO {

    private String email;
    private String password;
}

