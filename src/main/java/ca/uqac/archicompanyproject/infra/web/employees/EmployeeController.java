package ca.uqac.archicompanyproject.infra.web.employees;

import ca.uqac.archicompanyproject.domain.employees.Employee;
import ca.uqac.archicompanyproject.domain.employees.EmployeeService;
import ca.uqac.archicompanyproject.domain.secretary.Secretary;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    //ici pas sur qu'il faille que ca ca existe mais bon
    @GetMapping("/viewProfile")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Integer id) {

        try {
            Employee employee = employeeService.findEmployeeById(id);


            if (employee instanceof Secretary){
                //Traitement cas secrétaire
                int i = 0;
            } else {
                //Traitement cas médecin
                int i = 0 ;
            }

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
