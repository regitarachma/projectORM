package daos;

import entities.Department;
import icontrollers.IDepartmentController;
import idaos.IDepartmentDAO;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author RR17
 */
public class DepartmentController implements IDepartmentController{

    private IDepartmentDAO iddao;
    
    public DepartmentController(SessionFactory sessionFactory){
        iddao = new DepartmentDAO(sessionFactory);
    }

    @Override
    public List<Department> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return iddao.getAll();
    }

}
