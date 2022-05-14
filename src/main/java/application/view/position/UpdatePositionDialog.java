package application.view.position;

import application.exception.NonExistingEntityException;
import application.model.Employee;
import application.model.Position;
import application.service.PositionService;
import application.view.EntityDialog;

import java.util.Scanner;

public class UpdatePositionDialog implements EntityDialog<Position> {
    public static Scanner scanner = new Scanner(System.in);
    private PositionService positionService;

    public UpdatePositionDialog(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public Position input() throws NonExistingEntityException {
        Position position = null;
        while (position == null) {
            System.out.println("ID на позиция:");
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
        position.setName(null);
        while (position.getName() == null) {
            System.out.println("Име на позиция:");
            var answer = scanner.nextLine();
        }
        return position;
    }
}
