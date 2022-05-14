package application.controller.employeeManage;

import application.service.AccountService;
import application.service.ClientService;
import application.service.CurrencyService;
import application.view.Menu;
import application.view.account.*;

import java.util.List;
import java.util.Scanner;

public class AccountController {
    public static Scanner scanner = new Scanner(System.in);
    private AccountService accountService;
    private ClientService clientService;
    private CurrencyService currencyService;

    public AccountController(AccountService accountService, ClientService clientService, CurrencyService currencyService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.currencyService = currencyService;
    }

    public void init() {
        var menu = new Menu("Сметки", List.of(
                new Menu.Option("Виж всички сметки", () -> {
                    var accounts = accountService.getAll();
                    accounts.forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Добави сметка", () -> {
                    var created = new AddAccountDialog(clientService, currencyService).input();
                    accountService.add(created);
                    return "";
                }),
                new Menu.Option("Актуализирай сметка", () -> {
                    var updating = new UpdateAccountDialog(accountService, clientService, currencyService).input();
                    accountService.update(updating);
                    return "";
                }),
                new Menu.Option("Търсене на сметка по ID", () -> {
                    var account = new SearchAccountByIdDialog(accountService).input();
                    System.out.println(account);
                    return "";
                }),
                new Menu.Option("Търсене на сметка по име на клиент", () -> {
                    var accounts = new SearchAccountByClientNameDialog(accountService, clientService).input();
                    if(accounts.size()==0){
                        System.out.println("Няма налични сметки.");
                    }else{
                        accounts.forEach(System.out::println);
                    }
                    return "";
                }),
                new Menu.Option("Търсене на сметка по ЕГН на клиент", () -> {
                    var accounts = new SearchAccountByClientEGNDialog(accountService, clientService).input();
                    if(accounts.size()==0){
                        System.out.println("Няма налични сметки.");
                    }else{
                        accounts.forEach(System.out::println);
                    }
                    return "";
                }),
                new Menu.Option("Изтрий сметка по ID", () -> {
                    var deleting = new DeleteAccountDialog(accountService).input();
                    accountService.delete(deleting.getId());
                    return "";
                })

        ));
        menu.show();
    }
}
