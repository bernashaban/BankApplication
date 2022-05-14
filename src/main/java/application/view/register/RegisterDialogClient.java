package application.view.register;

import application.exception.NonExistingEntityException;
import application.model.Client;
import application.view.EntityDialog;

import java.util.Scanner;

public class RegisterDialogClient implements EntityDialog<Client> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public Client input() throws NonExistingEntityException {
        Client client = new Client();
        while (client.getUsername() == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Потребителското име трябва да бъде между 2 и 15 символа.");
            } else {
                client.setUsername(answer);
            }
        }
        while (client.getPassword() == null) {
            System.out.println("Парола: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Паролата трябва да бъде между 2 и 15 символа.\"");
            } else {
                client.setPassword(answer);
            }
        }
        while (client.getName() == null) {
            System.out.println("Име и фамилия: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 20) {
                System.out.println("Грешка: Името и фамилията трябва да е между 2 и 20 символа.\"");
            } else {
                client.setName(answer);
            }
        }
        while (client.getEgn() == null) {
            System.out.println("ЕГН: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Грешка: ЕГН трябва да бъде точно 10 символа.\"");
            } else {
                client.setEgn(answer);
            }
        }
        while (client.getAddress() == null) {
            System.out.println("Адрес: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Адреса трябва да бъде между 2 и 15 символа.\"");
            } else {
                client.setAddress(answer);
            }
        }
        while (client.getPhone() == null) {
            System.out.println("Телефон: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Грешка: Телефона трябва да бъде точно 10 символа\"");
            } else {
                client.setPhone(answer);
            }
        }
        return client;
    }
}
