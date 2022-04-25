package application.dao.impl;

import application.dao.Repository;

import application.exception.EntityPersistenceException;
import application.model.Client;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientRepository implements Repository<Integer, Client> {
    public static final String SELECT_ALL_CLIENTS = "select * from `client`;";
    public static final String INSERT_NEW_CLIENT = "insert into `client` (`client_name`, `client_egn`, `client_address`, `client_phone`) values (?, ?, ?, ?);";
    public static final String SELECT_CLIENT_BY_ID = "select * from `client` where idclient= ?;";
    public static final String UPDATE_CLIENT_BY_ID = "update `client` set `client_name` = ?, `client_egn` = ?, `client_address` = ?, `client_phone` = ? where idclient = ?;";
    public static final String DELETE_CLIENT_BY_ID = "delete from `client` where idclient = ?;";
    private Connection connection;

    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Client> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            var rs = stmt.executeQuery();
            return toClient(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CLIENTS, ex);
        }
    }

    @Override
    public Client findById(Integer id) {
        var clients = findAll();
        try (var stmt = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (Client client : clients) {
                Integer currentId = client.getId();
                if (currentId.equals(id)) {
                    return client;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CLIENTS, ex);
        }
    }

    @Override
    public Client create(Client entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getEgn());
            stmt.setString(3, entity.getAddress());
            stmt.setString(4, entity.getPhone());
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
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_CLIENTS, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CLIENTS, ex);
        }
    }

    @Override
    public Client update(Client entity) {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_CLIENT_BY_ID);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getEgn());
            stmt.setString(3, entity.getAddress());
            stmt.setString(4, entity.getPhone());
            stmt.setInt(5, old.getId());

            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CLIENTS, e);
        }
        return old;
    }

    @Override
    public Client deleteById(Integer id) {
        var account = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_CLIENTS, ex);
        }
        return account;
    }

    public List<Client> toClient(ResultSet rs) throws SQLException {
        List<Client> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Client(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return results;
    }
}
