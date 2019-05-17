package controllers;

import daos.JobDAO;
import entities.Job;
import icontrollers.IJobController;
import idaos.IJobDAO;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author RR17
 */
public class JobController implements IJobController{

    private IJobDAO ijdao;
    
    public JobController(SessionFactory sessionFactory){
        ijdao = new JobDAO(sessionFactory);
    }
    
    @Override
    public List<Job> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ijdao.getAll();
    }
    
}
