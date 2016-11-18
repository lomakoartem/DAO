package dao;
import java.util.List;

/**
 * Created by lomak on 17.11.2016.
 */
public interface DaoCRUD<E,K> {
    List<E>  getEmployeeList();
    E getEmployeeById(K id);

    boolean insertIntoEmployee(E employee);
    boolean updateEmployee(E employee);
    boolean deleteEmployee(K id);
}
