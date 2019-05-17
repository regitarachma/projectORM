package idaos;

import entities.Country;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface ICountryDAO {
    public List<Country> getAll();
    public Country getById(String countryId);
    public boolean insert(Country country);
    
    public List<Country> search(String keyword);
    public boolean update(Country country);
    public boolean delete(Country country);
}
