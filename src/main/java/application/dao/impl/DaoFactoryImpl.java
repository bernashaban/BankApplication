package application.dao.impl;

import application.dao.DaoFactory;
import application.service.*;

import java.sql.Connection;

public class DaoFactoryImpl implements DaoFactory {

    @Override
    public AccountRepository createAccountRepository(Connection connection, ClientService clientService, CurrencyService currencyService) {
        return new AccountRepository(connection, clientService,currencyService);
    }

    @Override
    public ClientRepository createClientRepository(Connection connection) {
        return new ClientRepository(connection);
    }

    @Override
    public CurrencyRepository createCurrencyRepository(Connection connection) {
        return new CurrencyRepository(connection);
    }

    @Override
    public EmployeeRepository createEmployeeRepository(Connection connection, PositionService positionService) {
        return new EmployeeRepository(connection,positionService);
    }

    @Override
    public PositionRepository createPositionRepository(Connection connection) {
        return new PositionRepository(connection);
    }

    @Override
    public TransactionRepository createTransactionRepository(Connection connection, TransTypeService transTypeService, EmployeeService employeeService, AccountService accountService) {
        return new TransactionRepository(connection, transTypeService, employeeService,accountService);
    }

    @Override
    public TransTypeRepository createTransTypeRepository(Connection connection) {
        return new TransTypeRepository(connection);
    }
}
