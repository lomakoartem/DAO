package dao;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by lomak on 17.11.2016.
 */
public class EmployeeDaoImpl implements DaoCRUD<Employee, Integer> {

    private Connection connection;
    private Statement statement;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<Employee> getEmployeeList() {

        String query = "SELECT * FROM company";
        ResultSet rs = null;
        ArrayList employeeList = new ArrayList();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address").trim(),
                        rs.getDouble("salary"));
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = null;
    /*  if (!checkIsInDB(id)) System.out.println("id Not in DB");
        else {*/

          String query = "SELECT * FROM company WHERE id = " + id;
          ResultSet rs = null;
          try {
              statement = connection.createStatement();
              rs = statement.executeQuery(query);
              while (rs.next()) {
                  employee = new Employee(rs.getInt("id"),
                          rs.getString("name"),
                          rs.getInt("age"),
                          rs.getString("address").trim(),
                          rs.getDouble("salary"));
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              DBUtil.close(rs);
              DBUtil.close(statement);
              DBUtil.close(connection);
          }

        return employee;
    }

    @Override
    public boolean insertIntoEmployee(Employee employee) {
        boolean isInsert = false;
        /*if (checkIsInDB(employee.getId())) System.out.println("id Alredy exist in DB");
        else {*/
            try {
                statement = connection.createStatement();
                PreparedStatement preparedStatement =
                        connection.prepareStatement
                                ("INSERT INTO company (id ,name,age,address,salary) VALUES (?, ?, ?,?,?)");

                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setInt(3, employee.getAge());
                preparedStatement.setString(4, employee.getAddress());
                preparedStatement.setDouble(5, employee.getSalary());
                preparedStatement.executeUpdate();
                isInsert = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(statement);
                DBUtil.close(connection);
            }

        return isInsert;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
         boolean isUpdated = false;
      /*  if (!checkIsInDB(employee.getId()))
        {
            System.out.println("id Not in DB");
        }*/
        try {
            statement = connection.createStatement();
                       PreparedStatement ps =
                    connection.prepareStatement
                            ("UPDATE Company SET name = ?, age = ?, address = ?, salary = ?" + " WHERE id = ?");
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getAge());
            ps.setString(3, employee.getAddress());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getId());
            ps.executeUpdate();
            isUpdated = true;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
      boolean isDelete = false;
    /*    if (!checkIsInDB(id))
        {
            System.out.println("id Not in DB");
        }*/
     /*   else {*/
            String query = "DELETE  FROM company WHERE id = " + id;
            ResultSet rs = null;
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
                isDelete = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(rs);
                DBUtil.close(statement);
                DBUtil.close(connection);
            }
        /*}*/
        return isDelete;
    }
/*public boolean checkIsInDB(Integer id)
{
    boolean isInDB = false;
    String query = "SELECT  FROM company WHERE id = " + id;
    try {
        statement = connection.createStatement();
        if (statement.execute(query))
        isInDB = true;
            } catch (SQLException e) {
        e.printStackTrace();
    }

    return isInDB;
}*/
}
