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
    private PositionService positionService;

    public LogInController(ClientService clientService, EmployeeService employeeService, AccountService accountService, TransactionService transactionService, CurrencyService currencyService, TransTypeService transTypeService, PositionService positionService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
        this.transTypeService = transTypeService;
        this.positionService = positionService;
    }

    public void init() {
        var menu = new Menu("Вход", List.of(
                new Menu.Option("Вход като клиент", () -> {
                    var user = new LogInDialogClient(clientService).input();
                    if (user == null) {
                        System.out.println("Неуспешен вход. Опитайте пак.");
                    } else {
                        ClientController clientController = new ClientController(user, clientService, accountService, transactionService, currencyService,employeeService, transTypeService);
                        clientController.init();
                    }
                    return "Успешен вход.";
                }),
                new Menu.Option("Вход като служител", () -> {
                    var user = new LogInDialogEmployee(employeeService).input();
                    if (user == null) {
                        System.out.println("Неуспешен вход. Опитайте пак.");
                    } else {
                        EmployeeController employeeController = new EmployeeController(user, employeeService, clientService, accountService,transactionService, currencyService, transTypeService, positionService);
                        employeeController.init();
                    }
                    return "Успешен вход.";
                })
        ));
        menu.show();
    }
}