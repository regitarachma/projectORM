package controllers;

import daos.CountryDAO;
import entities.Country;
import entities.Region;
import icontrollers.ICountryController;
import idaos.ICountryDAO;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author RR17
 */
public class CountryController implements ICountryController{
    private ICountryDAO icdao;
    
    public CountryController(SessionFactory sessionFactory){
        icdao = new CountryDAO(sessionFactory);
    }

    @Override
    public List<Country> getAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return icdao.getAll();
    }

    @Override
    public Country getById(String countryId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return icdao.getById(countryId);
    }

    @Override
    public String insert(String countryId, String countryName, String regionId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Country country = new Country(countryId, countryName, new Region(new BigDecimal(regionId)));
        String result="Insert Failed";
        if (icdao.insert(country)){
            result = "Insert Success";
        } 
//        else {
//            result = "Insert Failed";
//        }
        return result;
    }

    @Override
    public List<Country> search(String keyword) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return icdao.search(keyword);
    }

    @Override
    public String update(String countryId, String countryName, Region regionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(String countryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
