package application.dao.impl;

import application.dao.Repository;

import application.exception.EntityPersistenceException;
import application.model.TransactionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransTypeRepository implements Repository<Integer, TransactionType> {
    public static final String SELECT_ALL_TRANS_TYPES = "select * from `transaction_type`;";
    public static final String INSERT_NEW_TRANS_TYPE = "insert into `transaction_type` (`trans_type`, `coef`) values (?, ?);";
    public static final String SELECT_TRANS_TYPE_BY_ID ="select * from `transaction_type` where idtransaction_type = ?;";
    public static final String UPDATE_TRANS_TYPE_BY_ID="update `transaction_type` set `trans_type` = ?, `coef` = ? where idtransaction_type = ?;";
    public static final String DELETE_TRANS_TYPE_BY_ID="delete from `transaction_type` where idtransaction_type = ?;";
    private Connection connection;

    public TransTypeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<TransactionType> findAll() {
        try(var stmt = connection.prepareStatement(SELECT_ALL_TRANS_TYPES)) {
            var rs = stmt.executeQuery();
            return toTransType(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANS_TYPES, ex);
        }
    }

    @Override
    public TransactionType findById(Integer id) {
        var transactionTypes = findAll();
        try (var stmt = connection.prepareStatement(SELECT_TRANS_TYPE_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (TransactionType type : transactionTypes) {
                Integer currentId = type.getId();
                if(currentId.equals(id)){
                    return type;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANS_TYPES, ex);
        }
    }

    @Override
    public TransactionType create(TransactionType entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_TRANS_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getTypeName());
            stmt.setInt(2, entity.getCoefficient());
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
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_TRANS_TYPES, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANS_TYPES, ex);
        }
    }

    @Override
    public TransactionType update(TransactionType entity)  {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_TRANS_TYPE_BY_ID);
            stmt.setString(1, entity.getTypeName());
            stmt.setInt(2, entity.getCoefficient());
            stmt.setInt(3, old.getId());
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANS_TYPES, e);
        }
        return old;
    }

    @Override
    public TransactionType deleteById(Integer id)  {
        var transactionType = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_TRANS_TYPE_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_TRANS_TYPES, ex);
        }
        return transactionType;
    }

    public List<TransactionType> toTransType(ResultSet rs) throws SQLException {
        List<TransactionType> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new TransactionType(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
            ));
        }
        return results;
    }
}
