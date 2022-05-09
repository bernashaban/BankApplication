package application.view.logIn;


import application.model.Client;
import application.service.ClientService;
import application.view.EntityDialog;

import java.util.Scanner;

public class LogInDialogClient implements EntityDialog<Client> {
    public static Scanner scanner = new Scanner(System.in);
    public ClientService clientService;

    public LogInDialogClient(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client input() {
        Client client = null;
        while (client == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            try {
                client = clientService.findClientByUsername(answer);
            } catch (NullPointerException e) {
                System.out.println("Клиентът не съществува.");
            }
            if (client != null) {
                System.out.println("Парола:");
                var pass = scanner.nextLine();
                while (true) {
                    if (pass.equals(client.getPassword())) {
                        return client;
                    }
                    System.out.println("Грешна парола. Опитайте отново");
                    pass = scanner.nextLine();
                }
            } else {
                System.out.println("Грешка: Клиент с потребителско име '" + answer + "' не съществува.");
            }
        }
        System.out.println("Неуспешен вход.");
        return null;
    }

}
