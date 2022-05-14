package application.view.account;

import application.exception.NonExistingEntityException;
import application.model.Account;
import application.service.AccountService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchAccountByIdDialog implements EntityDialog<Account> {
    private AccountService accountService;
    public static Scanner scanner = new Scanner(System.in);

    public SearchAccountByIdDialog(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Account input() throws NonExistingEntityException {
        Account account = null;
        while (account == null) {
            System.out.println("ID на сметка: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            account =  accountService.getById(id);
            if(account==null){
                System.out.println("Сметка с такова ID не съществува!");
            }
        }
        return account;
    }
}
