package ca.uqac.archicompanyproject.infra.web.employees;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/secretaries")
//    public ResponseEntity<String> getSecretaries() {
//        try {
//            List<Secretary> employees = (List<Secretary>) userService.getSecretaries();
//            StringBuilder sb = new StringBuilder();
//            employees.forEach(employee -> sb.append(employee.getFirstName() +" " + employee.getLastName() + "\n"));
//            return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/secretaries/new")
//    public ResponseEntity<String> createNewSecretary() {
//        try {
//            Secretary secretary = Secretary.builder().firstName("Jeanne")
//                                                    .lastName("Dutronc")
//                                                    .salary(1234).password("ALLO").build();
//            userService.saveSecretary(secretary);
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/secretaries/update")
//    public ResponseEntity<String> updateSecretary() {
//        try {
//            Secretary secretary = Secretary.builder().firstName("Pierre- Paul")
//                    .lastName("Dutronc")
//                    .salary(1234).password("ALLO").ID(5).build();
//            userService.saveSecretary(secretary);
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/secretaries/delete")
//    public ResponseEntity<String> deleteSecretary() {
//        try {
//            Secretary secretary = Secretary.builder().ID(5).build();
//            userService.deleteSecretary(secretary);
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/caregivers")
    public ResponseEntity<String> getCaregivers() {
        try {
            List<Caregiver> employees = (List<Caregiver>) userService.getCaregivers();
            StringBuilder sb = new StringBuilder();
            employees.forEach(employee -> sb.append(employee.getFirstName() +" " + employee.getLastName() + "\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/viewProfile")
    public ResponseEntity<User> getEmployeeById(@RequestParam("id") Integer id) {

        Employee employee = userService.findEmployeeById(id);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        if (employee instanceof Secretary){
            //Traitement cas secrétaire
            int i = 0;
        } else {
            //Traitement cas médecin
            int i = 0 ;
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);




    }

}
