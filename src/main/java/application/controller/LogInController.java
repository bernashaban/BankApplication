package application.controller;

import application.service.*;
import application.view.logIn.LogInDialogClient;
import application.view.logIn.LogInDialogEmployee;
import application.view.Menu;

import java.util.List;
import java.util.Scanner;

public class LogInController {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;
    private EmployeeService employeeService;
    private AccountService accountService;
    private TransactionService transactionService;
    private CurrencyService currencyService;
    private TransTypeService transTypeService;

    public LogInController(ClientService clientService, EmployeeService employeeService, AccountService accountService, TransactionService transactionService, CurrencyService currencyService, TransTypeService transTypeService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
        this.transTypeService = transTypeService;
    }

    public void init() {
        var menu = new Menu("Log in", List.of(
                new Menu.Option("Log in for Client", () -> {
                    var user = new LogInDialogClient(clientService).input();
                    if (user == null) {
                        System.out.println("Login failed. Try again.");
                    } else {
                        ClientController clientController = new ClientController(user, clientService, accountService, transactionService, currencyService,employeeService, transTypeService);
                        clientController.init();
                    }
                    return "Logged in successfully.";
                }),
                new Menu.Option("Log in for Employee", () -> {
                    var user = new LogInDialogEmployee(employeeService).input();
                    if (user == null) {
                        System.out.println("Login failed. Try again.");
                    } else {
                        EmployeeController employeeController = new EmployeeController(user, employeeService, clientService, accountService, transactionService);
                        employeeController.init();
                    }
                    return "Logged in successfully.";
                })
        ));
        menu.show();
    }
}