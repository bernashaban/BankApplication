package application.controller.employeeManage;

import application.service.TransTypeService;
import application.view.Menu;
import application.view.transactionType.AddTransTypeDialog;
import application.view.transactionType.DeleteTransTypeDialog;
import application.view.transactionType.SearchTransTypeById;
import application.view.transactionType.UpdateTransTypeDialog;

import java.util.List;
import java.util.Scanner;

public class TransactionTypeController {
    public static Scanner scanner = new Scanner(System.in);
    private TransTypeService transTypeService;

    public TransactionTypeController(TransTypeService transTypeService) {
        this.transTypeService = transTypeService;
    }

    public void init() {
        var menu = new Menu("Типове транзакции", List.of(
                new Menu.Option("Виж всички типове транзакции", () -> {
                    transTypeService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Добави тип", () -> {
                    var created = new AddTransTypeDialog().input();
                    transTypeService.add(created);
                    return "Типът е добавен успшено";
                }),
                new Menu.Option("Актуализирай тип", () -> {
                    var updated = new UpdateTransTypeDialog(transTypeService).input();
                    transTypeService.update(updated);
                    return "Типът е актуализиран успшено!";
                }),
                new Menu.Option("Търсене на тип по ID", () -> {
                    var transType = new SearchTransTypeById(transTypeService).input();
                    System.out.println(transType);
                    return "";
                }),
                new Menu.Option("Изтриване на тип", () -> {
                    var deleted = new DeleteTransTypeDialog(transTypeService).input();
                    transTypeService.delete(deleted.getId());
                    return "Типът е изтрит успшено!";
                })
        ));
        menu.show();
    }
}
