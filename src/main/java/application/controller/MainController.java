package application.controller;

import application.service.*;
import application.view.Menu;

import java.util.List;

public class MainController {

    private final AccountService accountService;
    private final ClientService clientService;
    private final CurrencyService currencyService;
    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final TransactionService transactionService;
    private final TransTypeService transTypeService;

    public MainController(AccountService accountService, ClientService clientService, CurrencyService currencyService, EmployeeService employeeService, PositionService positionService, TransactionService transactionService, TransTypeService transTypeService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.currencyService = currencyService;
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.transactionService = transactionService;
        this.transTypeService = transTypeService;
    }

    public void init() {
        var menu = new Menu("Главно меню", List.of(
                new Menu.Option("Вход", () -> {
                    LogInController logInController = new LogInController(clientService, employeeService, accountService, transactionService,currencyService, transTypeService, positionService);
                    logInController.init();
                    return ("");
                }),
                new Menu.Option("Регистрация", () -> {
                    RegisterController registerController = new RegisterController(clientService, employeeService, positionService);
                    registerController.init();
                    return ("");
                })
        ));
        menu.show();
    }
}
