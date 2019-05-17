package daos;

import entities.Employee;
import entities.Job;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;
import idaos.IEmployeeDAO;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author RR17
 */
public class EmployeeDAO implements IEmployeeDAO {
    
    private SessionFactory sessionFactory = null; //masuk koneksi pintu utama (masuk ke table HR)
    private Session session = null; //masuk ke koneksi dalam yg sudah ada tujuan(hanya beberapa sesi2)
    private Transaction transaction = null;

    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Employee> employees = new ArrayList<>();
        try {
            session = sessionFactory.openSession(); //pembukaan sesinya
            transaction = session.beginTransaction(); //kemudian pembukaan session untuk transaksi
//            transaction.begin(); //mulai transaksi
            employees = session.createQuery("FROM Employee").list(); //jobs kita isikan dengan mengambil data dari object Region
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getById(BigDecimal employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> search(String keyword) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Employee> employees = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //getSimpleName adalah 
            String hql = "FROM " + Employee.class.getSimpleName() + " WHERE ";
            for (Field field : Employee.class.getDeclaredFields()) {
                if (!field.getName().contains("UID") && !field.getName().contains("List"))
                hql += field.getName()+ " LIKE '%" + keyword + "%' OR ";
            }
            hql = hql.substring(0, hql.lastIndexOf(" OR "));
            hql += " ORDER BY 1";
            employees = session.createQuery(hql).list();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public boolean insert(Employee employee) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            session.save(employee);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());

        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(Employee employee) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(employee);
            transaction.commit();
            result = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int employeeId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(new Employee(employeeId));
            transaction.commit();
            result = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
