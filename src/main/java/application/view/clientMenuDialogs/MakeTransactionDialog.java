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
            System.out.println("Account ID: ");
            var answer = scanner.nextLine();
            Integer accountId = 0;
            try {
                accountId = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format - numbers only.");
            }
            try {
                account = accountService.getById(accountId);
            } catch (NullPointerException e) {
                System.out.println("Account with ID = " + accountId + " does not exist.");
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
                System.out.println("Account with ID = " + accountId + " is not your. \nPlease select from your own accounts.");
            }
        }
        while (transaction.getAmount() == 0) {
            System.out.println("Amount: ");
            var answer = scanner.nextLine();
            double amount = 0;
            try {
                amount = Double.parseDouble(answer);
            } catch (NumberFormatException e) {
                System.out.println("Invalid money format - (Example:'50.99').");
            }
            transaction.setAmount(amount);

        }
        while (transaction.getType() == null) {
            System.out.println("Transaction type: ");
            System.out.println("1.Debit");
            System.out.println("2.Credit");
            var answer = scanner.nextLine();
            double currentBalance = 0;
            if (answer.equals("1")) {
                transaction.setType(transTypeService.getById(1));
                try {
                    currentBalance = account.getBalance();
                } catch (NullPointerException e) {
                    System.out.println("Current Account has no balance!");
                }
                account.setBalance(currentBalance + transaction.getAmount());
            } else if (answer.equals("2")) {
                transaction.setType(transTypeService.getById(2));
                try {
                    currentBalance = account.getBalance();
                } catch (NullPointerException e) {
                    System.out.println("Current Account has no balance!");
                }
                account.setBalance(currentBalance - transaction.getAmount());
            }else{
                System.out.println("Please choose '1' or '2' from the menu.");
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
