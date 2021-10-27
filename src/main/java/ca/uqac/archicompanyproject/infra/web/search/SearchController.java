package ca.uqac.archicompanyproject.infra.web.search;

import ca.uqac.archicompanyproject.domain.search.SearchResult;
import ca.uqac.archicompanyproject.domain.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/:request")
    @PreAuthorize("hasAnyRole('CAREGIVER','SECRETARY')")
    public ResponseEntity<List<SearchResult>> searchForEntityWithName(@RequestHeader(value = "Authorization") String token, @RequestParam("request") String name) {
        try {
            List<SearchResult> results = searchService.getEntitiesByName(name, token);
            return new ResponseEntity<>(results,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
