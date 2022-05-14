package application.controller;

import application.model.Client;
import application.service.*;
import application.view.Menu;
import application.view.clientMenuDialogs.*;

import java.util.List;
import java.util.Scanner;

public class ClientController {
    public static Scanner scanner = new Scanner(System.in);
    private Client client;
    private ClientService clientService;
    private AccountService accountService;
    private TransactionService transactionService;
    private CurrencyService currencyService;
    private EmployeeService employeeService;
    private TransTypeService transTypeService;

    public ClientController(Client client, ClientService clientService, AccountService accountService, TransactionService transactionService, CurrencyService currencyService, EmployeeService employeeService, TransTypeService transTypeService) {
        this.client = client;
        this.clientService = clientService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
        this.employeeService = employeeService;
        this.transTypeService = transTypeService;
    }

    public void init() {
        var menu = new Menu("Клиент", List.of(
                new Menu.Option("Лична информация", () -> {
                    System.out.println(client);
                    return "";
                }),
                new Menu.Option("Редактирай лична информация", () -> {
                    var updating = new UpdateDataDialog(clientService, client).input();
                    clientService.update(updating);
                    return "";
                }),
                new Menu.Option("Виж всички сметки", () -> {
                    //трябва да се изведат всички сметки на текущия клиент
                    var accounts = accountService.getClientAccounts(client);
                    accounts.forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Търсене на сметка по валута", () -> {
                    //клиента избира валута и се извеждат всички негови сметки със тази валута
                    var currency = new SearchCurrencyDialog(currencyService).input();
                    var accounts = accountService.getClientAccountsByCurrency(client, currency);
                    accounts.forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Направи транзакция", () -> {
                    var transaction = new MakeTransactionDialog(client, accountService, employeeService, transTypeService).input();
                    transactionService.add(transaction);
                    return "";
                }),
                new Menu.Option("Виж всички транзакции подредени по дата", () -> {
                    //извеждат се всички транзакции на клиента подредени по дата
                    var allTransactions = new SearchAllTransactions(client, transactionService).input();
                    if (allTransactions == null || allTransactions.isEmpty()) {
                        System.out.println("Не са намерени транзакции.");
                    } else {
                        allTransactions.forEach(System.out::println);
                    }
                    return "";
                }),
                new Menu.Option("Виж всички дебитни транзакции", () -> {
                    //извеждат се всички транзакции на клиента които да дебитни
                    var debitTrans = new SearchAllDebitTransactions(client, transactionService).input();
                    if (debitTrans == null || debitTrans.isEmpty()) {
                        System.out.println("Не са намерени дебитни транзакции.");
                    } else {
                        debitTrans.forEach(System.out::println);
                    }
                    return "";
                }),
                new Menu.Option("Виж всички кредитни транзакции", () -> {
                    //извеждат се всички транзакции на клиента които са кредитни
                    var creditTrans = new SearchAllCreditTransactions(client, transactionService).input();
                    if (creditTrans == null || creditTrans.isEmpty()) {
                        System.out.println("Не са намерени кредитни транзакции.");
                    } else {
                        creditTrans.forEach(System.out::println);
                    }
                    return "";
                })
        ));
        menu.show();
    }
}
