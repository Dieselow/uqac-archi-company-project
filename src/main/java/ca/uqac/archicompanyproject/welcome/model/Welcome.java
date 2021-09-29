package ca.uqac.archicompanyproject.welcome.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Welcome {
    private String team;
    private String project;
    private String version;
    private List<String> members;
}