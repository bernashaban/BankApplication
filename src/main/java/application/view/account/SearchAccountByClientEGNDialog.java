package application.view.account;

import application.model.Account;
import application.service.AccountService;
import application.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchAccountByClientEGNDialog {
    private AccountService accountService;
    private ClientService clientService;
    public static Scanner scanner = new Scanner(System.in);

    public SearchAccountByClientEGNDialog(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    public List<Account> input() {
        List<Account> accounts = new ArrayList<>();
        System.out.println("ЕГН на клиент: ");
        var answer = scanner.nextLine();
        var client = clientService.findClientByEGN(answer);
        if (client != null) {
            var clientAccounts = accountService.getClientAccounts(client);
            accounts.addAll(clientAccounts);
        } else {
            System.out.println("Не съществува клиент с такова ЕГН!");
        }
        return accounts;
    }
}
