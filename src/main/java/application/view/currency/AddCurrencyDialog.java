package application.view.currency;

import application.exception.NonExistingEntityException;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;
import application.view.EntityDialog;

import java.util.Scanner;


public class AddCurrencyDialog implements EntityDialog<CurrencyType> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public CurrencyType input() throws NonExistingEntityException {
        var currency = new CurrencyType();
        while(currency.getName()==null){
            System.out.println("Име на Валута:");
            var answer = scanner.nextLine();
            currency.setName(answer);
        }
        while(currency.getShortName()==null){
            System.out.println("Кратко име на валута:");
            var answer = scanner.nextLine();
            currency.setName(answer.toUpperCase());
        }

        return currency;
    }
}
