package application.view.position;

import application.exception.NonExistingEntityException;
import application.model.Position;
import application.view.EntityDialog;

import java.util.Scanner;

public class AddPositionDialog implements EntityDialog<Position> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public Position input() throws NonExistingEntityException {
        var position = new Position();
        while (position.getName() == null) {
            System.out.println("Име на позиция:");
            var answer = scanner.nextLine();
            position.setName(answer);
        }
        return position;
    }
}
