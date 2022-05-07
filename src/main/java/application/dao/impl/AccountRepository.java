package application.dao.impl;

import application.dao.Repository;
import application.exception.EntityPersistenceException;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;
import application.service.ClientService;
import application.service.CurrencyService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountRepository implements Repository<Integer, Account> {
    public static final String SELECT_ALL_ACCOUNTS = "select * from `account`;";
    public static final String INSERT_NEW_ACCOUNT = "insert into `account` (`account_number`, `interest`, `balance`, `client_id`, `currency_id`) values (?, ?, ?, ?, ?);";
    public static final String SELECT_ACCOUNT_BY_ID = "select * from `account` where idaccount = ?;";
    public static final String UPDATE_ACCOUNT_BY_ID = "update `account` set `account_number` = ?, `interest` = ?, `balance` = ?, `client_id` = ?, `currency_id` = ? where idaccount = ?;";
    public static final String DELETE_ACCOUNT_BY_ID = "delete from `account` where idaccount = ?;";
    private Connection connection;
    private ClientService clientService;
    private CurrencyService currencyService;

    public AccountRepository(Connection connection, ClientService clientService, CurrencyService currencyService) {
        this.connection = connection;
        this.clientService = clientService;
        this.currencyService = currencyService;
    }

    @Override
    public Collection<Account> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_ACCOUNTS)) {
            var rs = stmt.executeQuery();
            return toAccount(rs);
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_ACCOUNTS, ex);
        }
    }

    @Override
    public Account findById(Integer id) {
        var accounts = findAll();
        try (var stmt = connection.prepareStatement(SELECT_ACCOUNT_BY_ID)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            for (Account account : accounts) {
                Integer currentId = account.getId();
                if (currentId.equals(id)) {
                    return account;
                }
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_ACCOUNTS, ex);
        }
    }

    @Override
    public Account create(Account entity) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getAccountNum());
            stmt.setDouble(2, entity.getInterest());
            stmt.setDouble(3, entity.getBalance());
            stmt.setInt(4, entity.getClient().getId());
            stmt.setInt(5, entity.getCurrencyType().getId());
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
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_ACCOUNTS, ex);
            }
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_ACCOUNTS, ex);
        }
    }

    @Override
    public Account update(Account entity) {
        var old = findById(entity.getId());
        try {
            var stmt = connection.prepareStatement(UPDATE_ACCOUNT_BY_ID);
            stmt.setInt(1, entity.getAccountNum());
            stmt.setDouble(2, entity.getInterest());
            stmt.setDouble(3, entity.getBalance());
            stmt.setInt(4, entity.getClient().getId());
            stmt.setInt(5, entity.getCurrencyType().getId());
            stmt.setInt(6, old.getId());

            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_ACCOUNTS, e);
        }
        return old;
    }

    @Override
    public Account deleteById(Integer id) {
        var account = findById(id);
        try (var stmt = connection.prepareStatement(DELETE_ACCOUNT_BY_ID)) {
            stmt.setInt(1, id);
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println("Error creating connection to DB");
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_ACCOUNTS, ex);
        }
        return account;
    }

    public List<Account> toAccount(ResultSet rs) throws SQLException {
        List<Account> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Account(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    clientService.getById(rs.getInt(5)),
                    currencyService.getById(rs.getInt(6))
            ));
        }
        return results;
    }

    public Collection<Account> findClientAccounts(Client client) {
        var accounts = findAll();
        Collection<Account> clientAccounts = new ArrayList<>();
        for (Account account : accounts) {
            Integer currentClientId = account.getClient().getId();
            if (currentClientId.equals(client.getId())) {
                clientAccounts.add(account);
            }
        }
        return clientAccounts;
    }

    public Collection<Account> findClientAccountsByCurrency(Client client, CurrencyType currencyType) {
        var accounts = findAll();
        Collection<Account> clientAccounts = new ArrayList<>();
        for (Account account : accounts) {
            Integer currentClientId = account.getClient().getId();
            Integer currentCurrencyId = account.getCurrencyType().getId();
            if (currentClientId.equals(client.getId())) {
                if (currentCurrencyId.equals(currencyType.getId())) {
                    clientAccounts.add(account);
                }
            }
        }
        return clientAccounts;
    }

}
