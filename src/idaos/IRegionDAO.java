/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idaos;

import entities.Region;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author RR17
 */
public interface IRegionDAO {
    public List<Region> getAll();
    public Region getById(BigDecimal regionId);
    public Region search(String keyword);
    public boolean insert(Region region);
    public boolean update(Region region);
    public boolean delete(Region region);
}
