package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Created by lomak on 17.11.2016.
 */
public class EmployeeDaoImpl implements DaoCRUD<Employee, Integer> {

    private Connection connection = ConnectionFactory.getConnection();
    private Statement statement;

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
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement
                            ("INSERT INTO company (id ,name,age,address,salary) VALUES (?, ?, ?,?,?)");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try {
                       PreparedStatement ps =
                    connection.prepareStatement
                            ("UPDATE Company SET name = ?, age = ?, address = ?, salary = ?" + " WHERE id = ?");
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getAge());
            ps.setString(3, employee.getAddress());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        String query = "DELETE  FROM company WHERE id = " + id;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return true;
    }
}
