package ca.uqac.archicompanyproject.domain.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private String entityType;

    private String name;

    private String root;

    private String displayName;

    @Override
    public String toString(){
        return "(" + entityType + ") " + name;
    }

    public String getDisplayName(){
        return this.toString();
    }
}