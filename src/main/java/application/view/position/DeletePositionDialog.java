package application.view.position;

import application.exception.NonExistingEntityException;
import application.model.Position;
import application.service.PositionService;
import application.view.EntityDialog;

import java.util.Scanner;

public class DeletePositionDialog implements EntityDialog<Position> {
    public static Scanner scanner = new Scanner(System.in);
    private PositionService positionService;

    public DeletePositionDialog(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public Position input() throws NonExistingEntityException {
        positionService.getAll().forEach(System.out::println);
        Position position = null;
        while (position == null) {
            System.out.println("ID на позиция: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            position = positionService.getById(id);
            if (position == null) {
                System.out.println("Позиция с такова ID не съществува!");
            }
        }
        return position;
    }
}
