package application.view.transactionType;

import application.exception.NonExistingEntityException;
import application.model.TransactionType;
import application.view.EntityDialog;

import java.util.Scanner;

public class AddTransTypeDialog implements EntityDialog<TransactionType> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public TransactionType input() throws NonExistingEntityException {
        var transType = new TransactionType();
        while (transType.getTypeName() == null) {
            System.out.println("Име на типа транзакция: ");
            var answer = scanner.nextLine();
            transType.setTypeName(answer);

        }
        while (transType.getCoefficient() == 0) {
            System.out.println("Коефициент: ");
            var answer = scanner.nextLine();
            int coef = 0;
            try {
                coef = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден номер! Моля въведете само числа.");
            }
            transType.setCoefficient(coef);
        }
        return transType;
    }
}
