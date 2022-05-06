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
            System.out.println("Username: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Username should be between 2 and 15 characters long.");
            } else {
                client.setUsername(answer);
            }
        }
        client.setPassword(null);
        while (client.getPassword() == null) {
            System.out.println("Password: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Password should be between 2 and 15 characters long.");
            } else {
                client.setPassword(answer);
            }
        }
        client.setName(null);
        while (client.getName() == null) {
            System.out.println("Name: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Name should be between 2 and 15 characters long.");
            } else {
                client.setName(answer);
            }
        }
        client.setEgn(null);
        while (client.getUsername() == null) {
            System.out.println("EGN: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Error: EGN should be 10 characters long.");
            } else {
                client.setEgn(answer);
            }
        }
        client.setAddress(null);
        while (client.getAddress() == null) {
            System.out.println("Address: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Address should be between 2 and 15 characters long.");
            } else {
                client.setAddress(answer);
            }
        }
        client.setPhone(null);
        while (client.getPhone() == null) {
            System.out.println("Phone: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Error: Username should be 10 characters long.");
            } else {
                client.setPhone(answer);
            }
        }
        return client;
    }
}
