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
            System.out.println("Кратко име на валута(на латиница, с главни букви): ");
            var answer = scanner.nextLine();
            try {
                currencyType = currencyService.getCurrencyByShortName(answer);
            } catch (NullPointerException e) {
                System.out.println("Валута с кратко име: "+ answer+" не съществува.");
            }
            if (currencyType == null) {
                System.out.println("Валута с кратко име: " +answer +" не съществува.");
            }
        }
        return currencyType;
    }
}
