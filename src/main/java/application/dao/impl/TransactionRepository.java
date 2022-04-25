package application.dao.impl;

import application.dao.Repository;

import application.exception.EntityPersistenceException;
import application.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionRepository extends AbstractRepository<Integer, Transaction> implements Repository<Integer, Transaction> {
    public static final String SELECT_ALL_TRANSACTIONS = "select * from `transaction`;";
    public static final String INSERT_NEW_TRANSACTION = "insert into `transaction` (`trans_type_id`, `employee_id`, `account_id`, `amount`, `trans_date`) values (?, ?, ?, ?, ?);";
    public static final String SELECT_TRANSACTION_BY_ID ="select * from `transaction` where idtransaction = ?;";
    public static final String UPDATE_TRANSACTION_BY_ID="update `transaction` set `trans_type_id` = ?, `employee_id` = ?, `account_id` = ?, `amount` = ?, `trans_date` = ? where idtransaction = ?;";
    public static final String DELETE_TRANSACTION_BY_ID="delete from `transaction` where idtransaction = ?;";
    private Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Transaction> findAll() {
        try(var stmt = connection.prepareStatement(SELECT_ALL_TRANSACTIONS)) {
            var rs = stmt.executeQuery();
            return toTransaction(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANSACTIONS, ex);
        }
    }

    @Override
    public Transaction findById(Integer id) {
        var transactions = findAll();
        try (var stmt = connection.prepareStatement(SELECT_TRANSACTION_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (Transaction transaction : transactions) {
                Integer currentId = transaction.getId();
                if(currentId.equals(id)){
                    return transaction;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANSACTIONS, ex);
        }
    }

    @Override
    public Transaction create(Transaction entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_TRANSACTION, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getType().getId());
            stmt.setInt(2, entity.getEmployee().getId());
            stmt.setInt(3, entity.getAccount().getId());
            stmt.setDouble(4, entity.getAmount());
            stmt.setTimestamp(5, entity.getTransactionDate());
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
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_TRANSACTIONS, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANSACTIONS, ex);
        }
    }

    @Override
    public Transaction update(Transaction entity)  {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_TRANSACTION_BY_ID);
            stmt.setInt(1, entity.getType().getId());
            stmt.setInt(2, entity.getEmployee().getId());
            stmt.setInt(3, entity.getAccount().getId());
            stmt.setDouble(4, entity.getAmount());
            stmt.setTimestamp(5, entity.getTransactionDate());
            stmt.setInt(6, old.getId());

            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANSACTIONS, e);
        }
        return old;
    }

    @Override
    public Transaction deleteById(Integer id)  {
        var transaction = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_TRANSACTION_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANSACTIONS, ex);
        }
        return transaction;
    }

    public List<Transaction> toTransaction(ResultSet rs) throws SQLException {
        List<Transaction> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Transaction(
                    rs.getInt(1),
                    new TransactionType(),//transTypeService.findById(rs.getInt(2))
                    new Employee(),//employeeService.findById(rs.getInt(3))
                    new Account(),//accountService.findById(rs.getInt(4))
                    rs.getDouble(5),
                    rs.getTimestamp(6)
            ));
        }
        return results;
    }
}
