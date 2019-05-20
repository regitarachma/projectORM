package daos;

import entities.Location;
import idaos.IGeneralDAO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author RR17
 */
public class GeneralDAO<T> implements IGeneralDAO<T> {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;
    private Class general = null;

    public GeneralDAO(Class general, SessionFactory sessionFactory) {
        this.general = general;
        this.sessionFactory = sessionFactory;
    }

    private String create_hql(Object keyword){
        String hql = "FROM " + general.getSimpleName() + " WHERE ";
        for (Field field : general.getDeclaredFields()){
            if (!field.getName().contains("UID") && !field.getName().contains("List")){
                hql += field.getName() + " LIKE '%" + keyword + "%' OR ";
        }
    }
    hql = hql.substring(0, hql.lastIndexOf(" OR "));
    hql += " ORDER BY 1";
    return hql;
}
    
    @Override
    public List<T> getData(Object key, boolean isGetById) {
        List<T> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession(); //pembukaan sesinya
            transaction = session.beginTransaction(); //kemudian pembukaan session untuk transaksi
            
            if(isGetById){
                Query query = session.createQuery("FROM " + general.getSimpleName() + " WHERE "
                + general.getSimpleName().toLowerCase() +"_id=:keyword ORDER BY 1");
                query.setParameter("keyword", key);
                list = query.list();
            } else {
                list = session.createQuery(create_hql(key)).list();
            }
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
        return list;
    }

    @Override
    public boolean saveOrDelete(T model, boolean isSave) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if (isSave) {
                session.saveOrUpdate(model);
            } else {
                session.delete(model);
            }
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
