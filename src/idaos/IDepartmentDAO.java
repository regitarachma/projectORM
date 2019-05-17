package idaos;

import entities.Department;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface IDepartmentDAO {
    public List<Department> getAll();
    public Department getById(BigDecimal departmentId);
    public List<Department> search(String keyword);
    public boolean insert(Department department);
    public boolean update(Department department);
    public boolean delete(Department department);
}
