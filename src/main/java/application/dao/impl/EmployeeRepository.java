package application.dao.impl;

import application.dao.Repository;

import application.exception.EntityPersistenceException;
import application.model.*;
import application.service.PositionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeRepository implements Repository<Integer, Employee> {
    public static final String SELECT_ALL_EMPLOYEES = "select * from `employee`;";
    public static final String INSERT_NEW_EMPLOYEE = "insert into `employee` (`employee_username`,`employee_password`,`employee_name`, `employee_phone`, `position_id`) values (?, ?, ?, ?, ?);";
    public static final String SELECT_EMPLOYEE_BY_ID = "select * from `employee` where idemployee = ?;";
    public static final String UPDATE_EMPLOYEE_BY_ID = "update `employee` set `employee_username` = ?,`employee_password`=?, `employee_name` = ?, `employee_phone` = ?, `position_id` = ? where idemployee = ?;";
    public static final String DELETE_EMPLOYEE_BY_ID = "delete from `employee` where idemployee = ?;";
    public static final String SELECT_EMPLOYEE_BY_USERNAME = "select * from `employee` where username = ?;";
    private Connection connection;
    private PositionService positionService;

    public EmployeeRepository(Connection connection, PositionService positionService) {
        this.connection = connection;
        this.positionService = positionService;
    }

    @Override
    public Collection<Employee> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            var rs = stmt.executeQuery();
            return toEmployee(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, ex);
        }
    }

    @Override
    public Employee findById(Integer id) {
        var employees = findAll();
        try (var stmt = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (Employee employee : employees) {
                Integer currentId = employee.getId();
                if (currentId.equals(id)) {
                    return employee;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, ex);
        }
    }

    @Override
    public Employee create(Employee entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getPhone());
            stmt.setInt(5, entity.getPosition().getId());
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating appointment failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                    return entity;
                } else {
                    throw new EntityPersistenceException("Creating appointment failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_EMPLOYEES, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, ex);
        }
    }

    @Override
    public Employee update(Employee entity) {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_EMPLOYEE_BY_ID);
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getPhone());
            stmt.setInt(5, entity.getPosition().getId());
            stmt.setInt(6, old.getId());
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, e);
        }
        return old;
    }

    @Override
    public Employee deleteById(Integer id) {
        var account = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, ex);
        }
        return account;
    }

    public List<Employee> toEmployee(ResultSet rs) throws SQLException {
        List<Employee> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    positionService.getById(rs.getInt(6))
            ));
        }
        return results;
    }
    public Employee findByUsername(String username) {
        var employees = findAll();
        try (var stmt = connection.prepareStatement(SELECT_EMPLOYEE_BY_USERNAME)) {
            stmt.setString(1, username);
            var rs = stmt.executeQuery();
            for (Employee employee : employees) {
                String currentUsername = employee.getUsername();
                if(currentUsername.equals(username)){
                    return employee;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_EMPLOYEES, ex);
        }
    }
}
