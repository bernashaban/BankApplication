package application;

import application.controller.MainController;
import application.dao.DaoFactory;
import application.dao.impl.*;
import application.service.*;
import application.util.Jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

import static application.util.JdbcUtils.closeConnection;
import static application.util.JdbcUtils.createDbConnection;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        String dbConfigPath = Jdbc.class.getClassLoader().getResource("jdbc.properties").getPath();
        try {
            props.load(new FileInputStream(dbConfigPath));
        } catch (IOException e) {
            System.out.println("Error loading files.");
        }
        Connection conn = null;
        try {
            conn = createDbConnection(props);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creating connection with the database.");
        }


        DaoFactory daoFactory = new DaoFactoryImpl();

        ClientRepository clientRepository = daoFactory.createClientRepository(conn);
        ClientService clientService = new ClientService(clientRepository);

        CurrencyRepository currencyRepository = daoFactory.createCurrencyRepository(conn);
        CurrencyService currencyService = new CurrencyService(currencyRepository);

        PositionRepository positionRepository = daoFactory.createPositionRepository(conn);
        PositionService positionService = new PositionService(positionRepository);

        TransTypeRepository transTypeRepository = daoFactory.createTransTypeRepository(conn);
        TransTypeService transTypeService =new TransTypeService(transTypeRepository);

        AccountRepository accountRepository = daoFactory.createAccountRepository(conn,clientService, currencyService);
        AccountService accountService = new AccountService(accountRepository);

        EmployeeRepository employeeRepository = daoFactory.createEmployeeRepository(conn,positionService);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        TransactionRepository transactionRepository=daoFactory.createTransactionRepository(conn, transTypeService,employeeService,accountService);
        TransactionService transactionService = new TransactionService(transactionRepository);

        var mainController = new MainController(accountService,clientService,currencyService,employeeService,positionService,transactionService,transTypeService);
        mainController.init();

        try {
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error while closing the connection with database.");
        }

    }
}
