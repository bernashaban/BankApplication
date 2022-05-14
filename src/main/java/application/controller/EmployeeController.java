package application.controller;

import application.controller.employeeManage.*;
import application.model.Employee;
import application.service.*;
import application.view.Menu;
import application.view.employeeMenuDialogs.UpdateDataDialogEmp;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    public static Scanner scanner = new Scanner(System.in);
    private Employee employee;
    private EmployeeService employeeService;
    private ClientService clientService;
    private AccountService accountService;
    private TransactionService transactionService;
    private CurrencyService currencyService;
    private TransTypeService transTypeService;
    private PositionService positionService;

    public EmployeeController(Employee employee, EmployeeService employeeService, ClientService clientService, AccountService accountService, TransactionService transactionService, CurrencyService currencyService, TransTypeService transTypeService, PositionService positionService) {
        this.employee = employee;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
        this.transTypeService = transTypeService;
        this.positionService = positionService;
    }

    public void init() {
        var menu = new Menu("Служител", List.of(
                new Menu.Option("Лична информация", () -> {
                    System.out.println(employee);
                    return "";
                }),
                new Menu.Option("Редактирай лична информация", () -> {
                   var updating = new UpdateDataDialogEmp(employeeService, employee).input();
                    employeeService.update(updating);
                    return "";
                }),
                new Menu.Option("Управление на сметки", () -> {
                    AccountController accountController = new AccountController(accountService, clientService, currencyService);
                    accountController.init();
                    return "";
                }),
                new Menu.Option("Управление на транзакции", () -> {
                    TransactionController transactionController = new TransactionController(transactionService);
                    transactionController.init();
                    return "";
                }),
                new Menu.Option("Управление на валути", () -> {
                    CurrencyController currencyController = new CurrencyController(currencyService);
                    currencyController.init();
                    return "";
                }),
                new Menu.Option("Управление на типове транзакции", () -> {
                    TransactionTypeController transactionTypeController = new TransactionTypeController(transTypeService);
                    transactionTypeController.init();
                    return "";
                }),
                new Menu.Option("Управление на служители", () -> {
                    EmpEmployeeController employeeController = new EmpEmployeeController();
                    employeeController.init();
                    return "";
                }),
                new Menu.Option("Управление на клиенти", () -> {
                    EmpClientController clientController = new EmpClientController(clientService);
                    clientController.init();
                    return "";
                }),
                new Menu.Option("Управление на позиции", () -> {
                    PositionController positionController = new PositionController(positionService);
                    positionController.init();
                    return "";
                })
        ));
        menu.show();
    }
}
