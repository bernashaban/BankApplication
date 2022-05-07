package application.view.clientMenuDialogs;
import application.model.CurrencyType;
import application.service.CurrencyService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchCurrencyDialog implements EntityDialog<CurrencyType> {
    public static Scanner scanner = new Scanner(System.in);
    public CurrencyService currencyService;

    public SearchCurrencyDialog(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public CurrencyType input() {
        CurrencyType currencyType = null;
        while (currencyType == null) {
            System.out.println("Currency short name: ");
            var answer = scanner.nextLine();
            try {
                currencyType = currencyService.getCurrencyByShortName(answer);
            } catch (NullPointerException e) {
                System.out.println("Currency "+ answer+" does not exist.");
            }
            if (currencyType == null) {
                System.out.println("Currency "+ answer+" does not exist.");
            }
        }
        return currencyType;
    }
}
