package ca.uqac.archicompanyproject.domain.employees;

import javassist.NotFoundException;

public interface EmployeeService {
    Employee findEmployeeById(Integer id) throws NotFoundException;
    Employee findEmployeeByEmail(String email) throws  NotFoundException;
    Iterable<Employee> getEmployees();
}
