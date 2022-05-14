package application.controller.employeeManage;

import application.service.AccountService;
import application.service.EmployeeService;
import application.service.TransTypeService;
import application.service.TransactionService;
import application.view.Menu;
import application.view.transaction.AddTransactionDialog;
import application.view.transaction.DeleteTransactionDialog;
import application.view.transaction.SearchTransactionByIdDialog;
import application.view.transaction.UpdateTransactionDialog;

import java.util.List;
import java.util.Scanner;

public class TransactionController {
    public static Scanner scanner = new Scanner(System.in);
    private TransactionService transactionService;
    private AccountService accountService;
    private TransTypeService transTypeService;
    private EmployeeService employeeService;

    public TransactionController(TransactionService transactionService, AccountService accountService, TransTypeService transTypeService, EmployeeService employeeService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.transTypeService = transTypeService;
        this.employeeService = employeeService;
    }

    public void init() {
        var menu = new Menu("Транзакции", List.of(
                new Menu.Option("Виж всички транзакции", () -> {
                    transactionService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Добави транзакция", () -> {
                    var created = new AddTransactionDialog(transTypeService, employeeService, accountService).input();
                    transactionService.add(created);
                    return "Транзакцията е добавена успешно!";
                }),
                new Menu.Option("Актуализирай транзакция", () -> {
                    var updated = new UpdateTransactionDialog(transactionService, transTypeService, employeeService, accountService).input();
                    transactionService.update(updated);
                    return "Транзакцията е актуализирана успшено!";
                }),
                new Menu.Option("Търсене на транзакция по ID", () -> {
                    var transaction = new SearchTransactionByIdDialog(transactionService).input();
                    System.out.println(transaction);
                    return "";
                }),

                new Menu.Option("Изтриване на транзакция", () -> {
                    var deleted = new DeleteTransactionDialog(transactionService).input();
                    transactionService.delete(deleted.getId());
                    return "Транзакцията е изтрита успешно!";
                })

        ));
        menu.show();
    }
}
