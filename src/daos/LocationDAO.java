package daos;

import entities.Location;
import entities.Region;
import idaos.ILocationDAO;
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
public class LocationDAO implements ILocationDAO {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    public LocationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        try {
            session = sessionFactory.openSession(); //pembukaan sesinya
            transaction = session.beginTransaction(); //kemudian pembukaan session untuk transaksi
//            transaction.begin(); //mulai transaksi
            locations = session.createQuery("FROM Location").list(); //Location kita isikan dengan mengambil data dari object Region
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
        return locations;
    }

    @Override
    public Location getById(int id) {
        Location location = new Location();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Location WHERE locationId=:1 ORDER BY 1");
            query.setParameter(1, id);
            location = (Location) query.uniqueResult();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return location;
    }

    @Override
    public List<Location> search(Object keyword) {
        List<Location> locations = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //getSimpleName adalah 
            String hql = "FROM " + Location.class.getSimpleName() + " WHERE ";
            for (Field field : Location.class.getDeclaredFields()) {
                if (!field.getName().contains("UID") && !field.getName().contains("List"))
                hql += field.getName()+ " LIKE '%" + keyword + "%' OR ";
            }
            hql = hql.substring(0, hql.lastIndexOf(" OR "));
            hql += " ORDER BY 1";
            locations = session.createQuery(hql).list();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return locations;
    }

    @Override
    public boolean insert(Location l) {
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(l);
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
    public boolean update(Location l) {
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(l);
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
    public boolean delete(int id) {
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(new Location((short)id, ""));
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
