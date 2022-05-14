package application.view.currency;

import application.exception.NonExistingEntityException;
import application.model.CurrencyType;
import application.service.CurrencyService;
import application.view.EntityDialog;

import java.util.Scanner;

public class UpdateCurrencyDialog implements EntityDialog<CurrencyType> {
    public static Scanner scanner = new Scanner(System.in);
    private CurrencyService currencyService;

    public UpdateCurrencyDialog(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public CurrencyType input() throws NonExistingEntityException {
        CurrencyType currencyType = null;
        while (currencyType==null){
            System.out.println("ID на валута: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            currencyType = currencyService.getById(id);
            if (currencyType == null) {
                System.out.println("Валута с такова ID не съществува!");
            }
        }
        currencyType.setName(null);
        while (currencyType.getName() == null) {
            System.out.println("Име на Валута:");
            var answer = scanner.nextLine();
            currencyType.setName(answer);
        }
        currencyType.setShortName(null);
        while (currencyType.getShortName() == null) {
            System.out.println("Кратко име на валута:");
            var answer = scanner.nextLine();
            currencyType.setName(answer.toUpperCase());
        }

        return currencyType;
    }
}
