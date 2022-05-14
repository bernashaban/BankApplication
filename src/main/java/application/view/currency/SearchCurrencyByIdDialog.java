package application.view.currency;

import application.exception.NonExistingEntityException;
import application.model.CurrencyType;
import application.service.CurrencyService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchCurrencyByIdDialog implements EntityDialog<CurrencyType> {
    public static Scanner scanner = new Scanner(System.in);
    private CurrencyService currencyService;

    public SearchCurrencyByIdDialog(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public CurrencyType input() throws NonExistingEntityException {
        CurrencyType currencyType = null;
        while (currencyType == null) {
            System.out.println("ID на валута: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            currencyType=currencyService.getById(id);
            if (currencyType == null) {
                System.out.println("Валута с такова ID не съществува!");
            }
        }
        return currencyType;
    }
}
