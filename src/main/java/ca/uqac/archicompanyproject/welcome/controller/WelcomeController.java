package ca.uqac.archicompanyproject.welcome.controller;

import ca.uqac.archicompanyproject.welcome.model.Welcome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/welcome")
@RestController
public class WelcomeController {
    @GetMapping
    public ResponseEntity<Welcome> welcome() {
        Welcome welcome = Welcome.builder()
                .team("Groupe 1")
                .project("Architecture des applications d'entreprise")
                .version("1.0.0")
                .members(Arrays.asList("Louis DUMONT","Matthis Villeneuve","Alexandre Do Pham","Suzanne Ducrot","Korantin Toczé"))
                .build();

        return new ResponseEntity<>(welcome, HttpStatus.OK);
    }
}
