package icontrollers;

import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author RR17
 */
public interface IGeneralController<T> {
    public List<T> getAll();
    
    public List<T> getById(String id);
    public List<T> search(String key);
    public String save(T model);
    public String delete(T model);
    
}
