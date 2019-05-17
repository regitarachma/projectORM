package icontrollers;

import entities.Country;
import entities.Region;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface ICountryController {
    public List<Country> getAll();
    public Country getById(String countryId);
    public String insert (String countryId, String countryName, String regionId);
    
    public List<Country> search(String keyword);
    public String update (String countryId, String countryName, Region regionId);
    public String delete (String countryId);
}
