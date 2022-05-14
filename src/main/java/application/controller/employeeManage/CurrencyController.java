package application.controller.employeeManage;

import application.service.CurrencyService;
import application.view.Menu;
import application.view.currency.AddCurrencyDialog;
import application.view.currency.DeleteCurrencyDialog;
import application.view.currency.SearchCurrencyByIdDialog;
import application.view.currency.UpdateCurrencyDialog;

import java.util.List;
import java.util.Scanner;

public class CurrencyController {
    public static Scanner scanner = new Scanner(System.in);
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void init() {
        var menu = new Menu("Валути", List.of(
                new Menu.Option("Виж всички валути", () -> {
                    currencyService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Добави валута", () -> {
                    var created = new AddCurrencyDialog().input();
                    currencyService.add(created);
                    return "Валутата е добавена успешно!";
                }),
                new Menu.Option("Актуализирай валута", () -> {
                    var updating = new UpdateCurrencyDialog(currencyService).input();
                    currencyService.update(updating);
                    return "Валутата е актуализирана успешно!";
                }),
                new Menu.Option("Търсене на валута по ID", () -> {
                    var currency = new SearchCurrencyByIdDialog(currencyService).input();
                    System.out.println(currency);
                    return "";
                }),
                new Menu.Option("Изтрий валута", () -> {
                    var deleting = new DeleteCurrencyDialog(currencyService).input();
                    currencyService.delete(deleting.getId());
                    return "Валутата е изтрита успешно!";
                })

        ));
        menu.show();
    }
}
