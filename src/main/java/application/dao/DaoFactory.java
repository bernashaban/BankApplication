package application.dao;

import application.dao.impl.*;
import application.service.*;

import java.sql.Connection;

public interface DaoFactory {
    AccountRepository createAccountRepository(Connection connection,ClientService clientService, CurrencyService currencyService);
    ClientRepository createClientRepository(Connection connection);
    CurrencyRepository createCurrencyRepository(Connection connection);
    EmployeeRepository createEmployeeRepository(Connection connection, PositionService positionService);
    PositionRepository createPositionRepository(Connection connection);
    TransactionRepository createTransactionRepository(Connection connection, TransTypeService transTypeService, EmployeeService employeeService, AccountService accountService);
    TransTypeRepository createTransTypeRepository(Connection connection);

}
