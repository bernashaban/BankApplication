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
        var menu = new Menu("Employee Menu", List.of(
                new Menu.Option("See personal data", () -> {
                    System.out.println(employee);
                    return "";
                }),
                new Menu.Option("Edit personal data", () -> {
                   var updating = new UpdateDataDialogEmp(employeeService, employee).input();
                    employeeService.update(updating);
                    return "Personal data updated successfully.";
                }),
                new Menu.Option("Manage accounts", () -> {
                    AccountController accountController = new AccountController(accountService);
                    accountController.init();
                    return "";
                }),
                new Menu.Option("Manage transactions", () -> {
                    TransactionController transactionController = new TransactionController(transactionService);
                    transactionController.init();
                    return "";
                }),
                new Menu.Option("Manage currency types", () -> {
                    CurrencyController currencyController = new CurrencyController(currencyService);
                    currencyController.init();
                    return "";
                }),
                new Menu.Option("Manage transaction types", () -> {
                    TransactionTypeController transactionTypeController = new TransactionTypeController(transTypeService);
                    transactionTypeController.init();
                    return "";
                }),
                new Menu.Option("Manage employees", () -> {
                    EmpEmployeeController employeeController = new EmpEmployeeController();
                    employeeController.init();
                    return "";
                }),
                new Menu.Option("Manage clients", () -> {
                    EmpClientController clientController = new EmpClientController();
                    clientController.init();
                    return "";
                }),
                new Menu.Option("Manage positions", () -> {
                    PositionController positionController = new PositionController(positionService);
                    positionController.init();
                    return "";
                }),
                new Menu.Option("Delete profile", () -> {
                    System.out.println("You are about to delete your account. Are you sure?");
                    System.out.println("(Yes/No):");
                    var answer = scanner.nextLine();
                    String lowerCaseAns = answer.toLowerCase();
                    if (lowerCaseAns.equals("yes")) {
                        employeeService.delete(employee.getId());
                        return "Deleted successfully.";
                    } else if (answer.equals("no")) {
                        System.out.println("Delete canceled.");
                        return "Delete canceled.";
                    } else {
                        System.out.println("Error: Invalid answer.");
                        return "Error: Invalid answer.";
                    }
                })
        ));
        menu.show();
    }
}
