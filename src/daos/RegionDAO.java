package daos;

import idaos.IRegionDAO;
import entities.Region;
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
public class RegionDAO implements IRegionDAO {

    private SessionFactory sessionFactory = null; //masuk koneksi pintu utama (masuk ke table HR)
    private Session session = null; //masuk ke koneksi dalam yg sudah ada tujuan(hanya beberapa sesi2)
    private Transaction transaction = null;

    public RegionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        try {
            session = sessionFactory.openSession(); //pembukaan sesinya
            transaction = session.beginTransaction(); //kemudian pembukaan session untuk transaksi
//            transaction.begin(); //mulai transaksi
            regions = session.createQuery("FROM Region").list(); //regions kita isikan dengan mengambil data dari object Region
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
        return regions;
    }

    @Override
    public Region getById(BigDecimal regionId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Region region = null;
        session = this.sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            String hql = "FROM Region WHERE regionId = :regionId";
            Query query = session.createQuery(hql);
            query.setParameter("regionId", regionId);
            region = (Region) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return region;
    }
    
    @Override
    public boolean insert(Region region) {
        boolean result = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            session.save(region);
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
    public Region search(String keyword) {
//        List<Region> regions = new ArrayList<>();
        Region region = null;
        session = this.sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            String hql = "FROM Region WHERE regionId LIKE :regionId OR regionName LIKE :regionName";
            Query query = session.createQuery(hql);
            query.setParameter("regionId", new BigDecimal(keyword));
            query.setParameter("regionName", keyword);
            region = (Region) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return region;
    }

    @Override
    public boolean update(Region region) {
        boolean result = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            session.update(region);
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
    public boolean delete(Region region) {
        boolean result = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            session.delete(region);
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
}
