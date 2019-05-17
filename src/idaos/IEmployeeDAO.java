package idaos;

import entities.Employee;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface IEmployeeDAO {
    public List<Employee> getAll();
    public Employee getById(BigDecimal employeeId);
    public List<Employee> search(String keyword);
    public boolean insert(Employee employee);
    public boolean update(Employee employee);
    
    public boolean delete(int employeeId);
}
