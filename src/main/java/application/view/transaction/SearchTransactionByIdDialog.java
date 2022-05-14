package application.view.transaction;

import application.exception.NonExistingEntityException;
import application.model.Transaction;
import application.service.TransactionService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchTransactionByIdDialog implements EntityDialog<Transaction> {
    public static Scanner scanner = new Scanner(System.in);
    private TransactionService transactionService;

    public SearchTransactionByIdDialog(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Transaction input() throws NonExistingEntityException {
        Transaction transaction = null;
        while (transaction == null) {
            System.out.println("ID на транзакция: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            transaction = transactionService.getById(id);
            if (transaction == null) {
                System.out.println("Транзакция с такова ID не съществува!");
            }
        }
        return transaction;
    }
}
