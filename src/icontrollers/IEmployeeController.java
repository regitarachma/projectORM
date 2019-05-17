package icontrollers;

import entities.Employee;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface IEmployeeController {
    public List<Employee> getAll();
    public Employee getById(String employeeId);
    public List<Employee> search(String keyword);
    public String insert (String employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String salary, String commissionPct, String departmentId, String managerId, String jobId);
    public String update (String employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String salary, String commissionPct, String departmentId, String managerId, String jobId);
    public String delete (String employeeId);
}
