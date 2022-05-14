package application.view.account;

import application.model.Account;
import application.model.Client;
import application.service.AccountService;
import application.service.ClientService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class SearchAccountByClientNameDialog {
    private AccountService accountService;
    private ClientService clientService;
    public static Scanner scanner = new Scanner(System.in);

    public SearchAccountByClientNameDialog(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    public List<Account> input() {
        List<Account> accounts = new ArrayList<>();
        System.out.println("Име на клиент: ");
        var answer = scanner.nextLine();
        Collection<Client> clients = clientService.findClientByName(answer);
        if (clients.size()!=0) {
            for (Client client : clients) {
                var clientAccounts = accountService.getClientAccounts(client);
                accounts.addAll(clientAccounts);
            }
        }else{
            System.out.println("Не съществува клиент с такова име!");
        }
        return accounts;
    }
}
