package controllers;

import daos.EmployeeDAO;
import entities.Department;
import entities.Employee;
import entities.Job;
import icontrollers.IEmployeeController;
import idaos.IEmployeeDAO;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;

/**
 *
 * @author RR17
 */
public class EmployeeController implements IEmployeeController {

//    Date d = new Date();
//    java.util.Date date = new java.util.Date();
//    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    Date date = new Date(); // this object contains the current date value 
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private IEmployeeDAO iedao;

    public EmployeeController(SessionFactory factory) {
        iedao = new EmployeeDAO(factory);
    }

    @Override
    public List<Employee> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return iedao.getAll();
    }

    @Override
    public Employee getById(String employeeId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return iedao.getById(BigDecimal.ONE);
    }

    @Override
    public List<Employee> search(String keyword) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return iedao.search(keyword);
    }

    @Override
    public String delete(String employeeId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String result = "Maaf data tidak bisa dihapus";
        if (iedao.delete(Integer.parseInt(employeeId))) {
            result = "Data berhasil dihapus";
        }
        return result;
    }

    @Override
    public String insert(String employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String salary, String commissionPct, String departmentId, String managerId, String jobId) {
        String result = "Maaf data gagal disimpan";
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            date = formatter.parse(hireDate);
            Employee employee = new Employee(Integer.parseInt(employeeId), firstName, lastName, email, phoneNumber, new java.sql.Date(date.getTime()), new BigDecimal(salary), new BigDecimal(commissionPct), new Department(Short.parseShort(departmentId)), new Employee(Integer.parseInt(managerId)), new Job(jobId));

            if (iedao.insert(employee)) {
                result = "Data Berhasil Disimpan";
            }

        } catch (ParseException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String update(String employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String salary, String commissionPct, String departmentId, String managerId, String jobId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String result = "Maaf data gagal diupdate";
        try {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            date = formatter.parse(hireDate);
            Employee employee = new Employee(Integer.parseInt(employeeId), firstName, lastName, email, phoneNumber, new java.sql.Date(date.getTime()), new BigDecimal(salary), new BigDecimal(commissionPct), new Department(Short.parseShort(departmentId)), new Employee(Integer.parseInt(managerId)), new Job(jobId));

            if (iedao.update(employee)) {
                result = "Data Berhasil Diupdate";
            }

        } catch (ParseException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

}
