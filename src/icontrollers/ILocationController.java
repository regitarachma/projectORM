package icontrollers;

import entities.Location;
import java.util.List;

/**
 *
 * @author RR17
 * @author Okala
 */
public interface ILocationController {
    public List<Location> getAll();
    public Location getById(String locationId);
    public List<Location> search(String keyword);
    public String insert(String locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId);
    public String update(String locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId);
    public String delete(String locationId);
}
