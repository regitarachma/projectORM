package idaos;

import entities.Location;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author RR17
 * @author Okala
 */
public interface ILocationDAO {

    public List<Location> getAll();

    public Location getById(int id);

    public List<Location> search(Object keyword);

    public boolean insert(Location l);

    public boolean update(Location l);

    public boolean delete(int id);
}