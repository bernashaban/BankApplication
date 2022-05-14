package application.view.transaction;

import application.exception.NonExistingEntityException;
import application.model.*;
import application.service.AccountService;
import application.service.EmployeeService;
import application.service.TransTypeService;
import application.view.EntityDialog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AddTransactionDialog implements EntityDialog<Transaction> {
    public static Scanner scanner = new Scanner(System.in);
    private TransTypeService transTypeService;
    private EmployeeService employeeService;
    private AccountService accountService;

    public AddTransactionDialog(TransTypeService transTypeService, EmployeeService employeeService, AccountService accountService) {
        this.transTypeService = transTypeService;
        this.employeeService = employeeService;
        this.accountService = accountService;
    }

    @Override
    public Transaction input() throws NonExistingEntityException {
        var transaction = new Transaction();
        Account account = null;
        while (transaction.getAccount() == null) {
            System.out.println("Сметка ID: ");
            var answer = scanner.nextLine();
            int accountId = 0;
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
            transaction.setAccount(account);
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
            } else {
                System.out.println("Моля изберете 1 или 2 от менюто.");
            }
        }
        Employee employee = null;
        while (transaction.getEmployee() == null) {
            System.out.println("Служител ID: ");
            var answer = scanner.nextLine();
            int employeeId = 0;
            try {
                employeeId = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Опитайте с цифри.");
            }
            try {
                employee = employeeService.getById(employeeId);
            } catch (NullPointerException e) {
                System.out.println("Служител с ID = " + employee + " не съществува.");
            }
            transaction.setEmployee(employee);
        }
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        transaction.setTransactionDate(timestamp);
        return transaction;
    }
}
