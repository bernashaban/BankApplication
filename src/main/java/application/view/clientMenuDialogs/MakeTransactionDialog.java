package application.view.clientMenuDialogs;

import application.exception.NonExistingEntityException;
import application.model.Account;
import application.model.Client;
import application.model.Employee;
import application.model.Transaction;
import application.service.AccountService;
import application.service.ClientService;
import application.service.EmployeeService;
import application.service.TransTypeService;
import application.view.EntityDialog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

public class MakeTransactionDialog implements EntityDialog<Transaction> {


    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;
    private Client client;
    private AccountService accountService;
    private EmployeeService employeeService;
    private TransTypeService transTypeService;

    public MakeTransactionDialog(ClientService clientService, Client client, AccountService accountService, EmployeeService employeeService, TransTypeService transTypeService) {
        this.clientService = clientService;
        this.client = client;
        this.accountService = accountService;
        this.employeeService = employeeService;
        this.transTypeService = transTypeService;
    }

    @Override
    public Transaction input() {
        var transaction = new Transaction();
        Account account = null;
        while (transaction.getAccount() == null) {
            System.out.println("Сметка ID: ");
            var answer = scanner.nextLine();
            Integer accountId = 0;
            try {
                accountId = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Опитайте с цифри.");
            }
            try {
                account = accountService.getById(accountId);
            } catch (NullPointerException e) {
                System.out.println("Сметка с ID = " + accountId + " не съществува.");
            }
            Collection<Account> allAccounts = accountService.getClientAccounts(client);
            boolean isAccountClients = false;
            for (Account clientAccount : allAccounts) {
                if(clientAccount.getId()==accountId){
                    isAccountClients=true;
                    transaction.setAccount(account);
                }
            }
            if(!isAccountClients){
                System.out.println("Сметка с ID = " + accountId + " не е ваша. \nМоля изберете сметка, която ви принадлежи.");
            }
        }
        while (transaction.getAmount() == 0) {
            System.out.println("Сума: ");
            var answer = scanner.nextLine();
            double amount = 0;
            try {
                amount = Double.parseDouble(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на пари - (Пример за валиден формат:'50.99').");
            }
            transaction.setAmount(amount);

        }
        while (transaction.getType() == null) {
            System.out.println("Тип на транзакцията: ");
            System.out.println("1.Дебит");
            System.out.println("2.Кредит");
            var answer = scanner.nextLine();
            double currentBalance = 0;
            if (answer.equals("1")) {
                transaction.setType(transTypeService.getById(1));
                try {
                    currentBalance = account.getBalance();
                } catch (NullPointerException e) {
                    System.out.println("В момента нямате наличен баланс по сметката!");
                }
                account.setBalance(currentBalance + transaction.getAmount());
            } else if (answer.equals("2")) {
                transaction.setType(transTypeService.getById(2));
                try {
                    currentBalance = account.getBalance();
                } catch (NullPointerException e) {
                    System.out.println("В момента нямате наличен баланс по сметката!");
                }
                account.setBalance(currentBalance - transaction.getAmount());
            }else{
                System.out.println("Моля изберете 1 или 2 от менюто.");
            }
        }
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        transaction.setTransactionDate(timestamp);
        int employeeCount = employeeService.getAll().size();
        int employeeId = (int) ((Math.random() * (employeeCount - 1)) + 1);
        transaction.setEmployee(employeeService.getById(employeeId));
        return transaction;
    }
}
