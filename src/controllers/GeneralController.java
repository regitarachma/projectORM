package controllers;

import daos.GeneralDAO;
import icontrollers.IGeneralController;
import idaos.IGeneralDAO;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.hibernate.SessionFactory;

/**
 *
 * @author RR17
 */
public class GeneralController<T> implements IGeneralController<T>{
    private IGeneralDAO igdao;
    
    public GeneralController(Class general, SessionFactory sessionFactory){
        igdao = new GeneralDAO<>(general, sessionFactory);
    }

    @Override
    public List<T> getAll() {
        return igdao.getData("", false);
    }

    @Override
    public List<T> getById(String id) {
        return igdao.getData(id, true);
    }

    @Override
    public List<T> search(String key) {
        return igdao.getData(key, false);
    }

    @Override
    public String save(T model) {
        String result = "Data gagal disimpan";
        if(igdao.saveOrDelete(model, true)){
            result = "Data berhasil disimpan";
        }
        return result;
    }

    @Override
    public String delete(T model) {
        String result = "Data gagal dihapus";
        if(igdao.saveOrDelete(model, true)){
            result = "Data berhasil disimpan";
        }
        return result;
    }
    
}
