package ca.uqac.archicompanyproject.domain.search;

import java.util.List;

public interface SearchService {
    List<SearchResult> getEntitiesByName(String name, String token);
}
