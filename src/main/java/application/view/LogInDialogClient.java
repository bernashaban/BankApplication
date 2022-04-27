package application.view;


import application.model.Client;
import application.service.ClientService;

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
            System.out.println("Username: ");
            var answer = scanner.nextLine();
            try {
                client = clientService.findClientByUsername(answer);
            } catch (NullPointerException e) {
                System.out.println("Client does not exist.");
            }
            if (client != null) {
                System.out.println("Password:");
                var pass = scanner.nextLine();
                while (true) {
                    if (pass.equals(client.getPassword())) {
                        return client;
                    }
                    System.out.println("Error: Wrong password. Try again.");
                    pass = scanner.nextLine();
                }
            } else {
                System.out.println("Error: User with username: '" + answer + "' does not exist.");
            }
        }
        System.out.println("The login failed.");
        return null;
    }

}
