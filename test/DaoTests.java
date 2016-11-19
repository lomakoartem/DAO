package test;

import dao.DaoCRUD;
import dao.Employee;
import dao.EmployeeDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lomak on 18.11.2016.
 */
public class DaoTests {

    DaoCRUD<Employee, Integer> daoCRUD = new EmployeeDaoImpl();
    Employee employee = new Employee(8, "Test", 22, "Test", 600);
    Employee employee1 = new Employee(17, "Test1", 22, "Test1", 3600);
    Employee employee2 = new Employee(57, "Test2", 22, "Test2", 2600);
    Employee employee3 = new Employee(77, "Test3", 22, "Test3", 4600);

    @Test
    public void testDaoDelete() {
        assertEquals(true, daoCRUD.deleteEmployee(1));
    }
    @Test
    public void testDaoInsert() {

      //  assertEquals(true, daoCRUD.insertIntoEmployee(employee2));
        assertEquals(true, daoCRUD.insertIntoEmployee(employee3));
     }
    @Test
    public void testDaoGetById()
    {
    assertEquals(employee,daoCRUD.getEmployeeById(8));
    }




}