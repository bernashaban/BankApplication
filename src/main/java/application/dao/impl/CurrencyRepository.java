package application.dao.impl;

import application.dao.Repository;
import application.exception.EntityPersistenceException;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CurrencyRepository implements Repository<Integer, CurrencyType> {
    public static final String SELECT_ALL_CURRENCY = "select * from `currency_type`;";
    public static final String INSERT_NEW_CURRENCY = "insert into `currency_type` (`currency_name`, `short_name`) values (?, ?);";
    public static final String SELECT_CURRENCY_BY_ID = "select * from `currency_type` where idcurrency = ?;";
    public static final String UPDATE_CURRENCY_BY_ID = "update `currency_type` set `currency_name` = ?, `short_name` = ? where idcurrency = ?;";
    public static final String DELETE_CURRENCY_BY_ID = "delete from `currency_type` where idcurrency = ?;";
    private Connection connection;

    public CurrencyRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<CurrencyType> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_CURRENCY)) {
            var rs = stmt.executeQuery();
            return toCurrency(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CURRENCY, ex);
        }
    }

    @Override
    public CurrencyType findById(Integer id) {
        var currencies = findAll();
        try (var stmt = connection.prepareStatement(SELECT_CURRENCY_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (CurrencyType currencyType : currencies) {
                Integer currentId = currencyType.getId();
                if (currentId.equals(id)) {
                    return currencyType;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CURRENCY, ex);
        }
    }

    @Override
    public CurrencyType create(CurrencyType entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_CURRENCY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getShortName());
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
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_CURRENCY, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CURRENCY, ex);
        }
    }

    @Override
    public CurrencyType update(CurrencyType entity) {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_CURRENCY_BY_ID);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getShortName());
            stmt.setInt(3, old.getId());
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CURRENCY, e);
        }
        return old;
    }

    @Override
    public CurrencyType deleteById(Integer id) {
        var account = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_CURRENCY_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CURRENCY, ex);
        }
        return account;
    }

    public List<CurrencyType> toCurrency(ResultSet rs) throws SQLException {
        List<CurrencyType> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new CurrencyType(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            ));
        }
        return results;
    }

    public CurrencyType findCurrencyByShortName(String shortName) {
        var currencyTypeCollection = findAll();
        for (CurrencyType currencyType : currencyTypeCollection) {

            if (currencyType.getShortName().equals(shortName)) {
                return currencyType;
            }
        }
        return null;
    }


}
