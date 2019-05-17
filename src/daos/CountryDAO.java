package daos;

import entities.Country;
import entities.Employee;
import entities.Location;
import idaos.ICountryDAO;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author RR17
 */
public class CountryDAO implements ICountryDAO {

    private SessionFactory sessionFactory = null; //masuk koneksi pintu utama (masuk ke table HR)
    private Session session = null; //masuk ke koneksi dalam yg sudah ada tujuan(hanya beberapa sesi2)
    private Transaction transaction = null;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Country> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Country> countries = new ArrayList<>();
        try {
            session = sessionFactory.openSession(); //pembukaan sesinya
            transaction = session.beginTransaction(); //kemudian pembukaan session untuk transaksi
//            transaction.begin(); //mulai transaksi
            countries = session.createQuery("FROM Country").list(); //countries kita isikan dengan mengambil data dari object Region
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
        return countries;
    }

    @Override
    public Country getById(String countryId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Country country = null;
        session = this.sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            String hql = "FROM Country WHERE countryId = :countryId";
            Query query = session.createQuery(hql);
            query.setParameter("countryId", countryId);
            country = (Country) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return country;
    }

    @Override
    public boolean insert(Country country) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
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
    public List<Country> search(String keyword) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Country> countries = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //getSimpleName adalah 
            String hql = "FROM " + Country.class.getSimpleName() + " WHERE ";
            for (Field field : Country.class.getDeclaredFields()) {
                if (!field.getName().contains("UID") && !field.getName().contains("List"))
                hql += field.getName()+ " LIKE '%" + keyword + "%' OR ";
            }
            hql = hql.substring(0, hql.lastIndexOf(" OR "));
            hql += " ORDER BY 1";
            countries = session.createQuery(hql).list();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return countries;
    }

    @Override
    public boolean update(Country country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Country country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
