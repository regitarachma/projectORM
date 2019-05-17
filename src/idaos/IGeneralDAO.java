package idaos;

import java.util.List;

/**
 *
 * @author RR17
 */
public interface IGeneralDAO<T> {
    public List<T> getData(Object key, boolean isGetById);
    public boolean saveOrDelete(T model, boolean isSave);
}
