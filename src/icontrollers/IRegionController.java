package icontrollers;

import entities.Region;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface IRegionController {
    public List<Region> getAll();
    public Region getById(String regionId);
    public List<Region> search(String keyword);
//    public String insert(String locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId);
//    public String update(String locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId);
//    public String delete(String locationId);
}
