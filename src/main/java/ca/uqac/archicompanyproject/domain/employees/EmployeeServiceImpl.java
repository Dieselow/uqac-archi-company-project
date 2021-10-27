package ca.uqac.archicompanyproject.domain.employees;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepositoryInterface employeeRepository;

    @Override
    public Employee findEmployeeById(Integer id) throws NotFoundException {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        }
        throw new NotFoundException("Employee with id " + id + " not found");
    }

    @Override
    public Employee findEmployeeByEmail(String email) throws  NotFoundException {

        Optional<Employee> employee = employeeRepository.findByEmail(email);

        if (employee.isPresent()) {
            return employee.get();
        }
        throw new NotFoundException("Employee with email:" + email + " not found");
    }

    @Override
    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
