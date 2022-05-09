package application.view.clientMenuDialogs;

import application.model.Client;
import application.service.ClientService;
import application.view.EntityDialog;

import java.util.Scanner;


public class UpdateDataDialog implements EntityDialog<Client> {
    private ClientService clientService;
    public static Scanner scanner = new Scanner(System.in);
    private Client client;

    public UpdateDataDialog(ClientService clientService, Client client) {
        this.clientService = clientService;
        this.client = client;
    }

    @Override
    public Client input() {
        client.setUsername(null);
        while (client.getUsername() == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Потребителското име трябва да бъде между 2 и 15 символа.");
            } else {
                client.setUsername(answer);
            }
        }
        client.setPassword(null);
        while (client.getPassword() == null) {
            System.out.println("Парола: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Паролата трябва да бъде между 2 и 15 символа.\"");
            } else {
                client.setPassword(answer);
            }
        }
        client.setName(null);
        while (client.getName() == null) {
            System.out.println("Име и фамилия: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Името и фамилията трябва да е между 2 и 20 символа.\"");
            } else {
                client.setName(answer);
            }
        }
        client.setAddress(null);
        while (client.getAddress() == null) {
            System.out.println("Адрес: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Адреса трябва да бъде между 2 и 15 символа.\"");
            } else {
                client.setAddress(answer);
            }
        }
        client.setPhone(null);
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
