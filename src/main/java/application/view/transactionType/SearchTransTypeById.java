package application.view.transactionType;

import application.exception.NonExistingEntityException;
import application.model.TransactionType;
import application.service.TransTypeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchTransTypeById implements EntityDialog<TransactionType> {
    public static Scanner scanner = new Scanner(System.in);
    private TransTypeService transTypeService;

    public SearchTransTypeById(TransTypeService transTypeService) {
        this.transTypeService = transTypeService;
    }

    @Override
    public TransactionType input() throws NonExistingEntityException {
        TransactionType transactionType = null;
        while (transactionType == null) {
            System.out.println("ID на тип транзакция: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            transactionType = transTypeService.getById(id);
            if (transactionType == null) {
                System.out.println("Тип транзакция с такова ID не съществува!");
            }
        }
        return transactionType;
    }
}
