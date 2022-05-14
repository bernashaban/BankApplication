package application.view.client;

import application.exception.NonExistingEntityException;
import application.model.Client;
import application.service.ClientService;
import application.view.EntityDialog;

import java.util.Scanner;

public class UpdateClientDialog implements EntityDialog<Client> {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;

    public UpdateClientDialog(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client input() throws NonExistingEntityException {
        Client client = null;
        while (client == null) {
            System.out.println("ID на клиент:");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            client = clientService.getById(id);
            if (client == null) {
                System.out.println("Сметка с такова ID не съществува!");
            }
        }
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
