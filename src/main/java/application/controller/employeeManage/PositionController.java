package application.controller.employeeManage;

import application.service.PositionService;
import application.view.Menu;
import application.view.position.AddPositionDialog;
import application.view.position.DeletePositionDialog;
import application.view.position.SearchPositionByIdDialog;
import application.view.position.UpdatePositionDialog;

import java.util.List;
import java.util.Scanner;

public class PositionController {
    public static Scanner scanner = new Scanner(System.in);
    private PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    public void init() {
        var menu = new Menu("Позиции", List.of(
                new Menu.Option("Виж всички позиции", () -> {
                    positionService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Добави позиция", () -> {
                    var created = new AddPositionDialog().input();
                    positionService.add(created);
                    return "Позицията е добавена успешно!";
                }),
                new Menu.Option("Актуализирай позиция", () -> {
                    var updating = new UpdatePositionDialog(positionService).input();
                    positionService.update(updating);
                    return "Позицията е актуализирана успшено!";
                }),
                new Menu.Option("Търси позиция по ID", () -> {
                    var position = new SearchPositionByIdDialog(positionService).input();
                    System.out.println(position);
                    return "";
                }),
                new Menu.Option("Изтрий позиция", () -> {
                    var deleted = new DeletePositionDialog(positionService).input();
                    positionService.delete(deleted.getId());
                    return "Позицията е изтрита успешно!";
                })

        ));
        menu.show();
    }
}
