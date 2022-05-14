package application.view.client;

import application.exception.NonExistingEntityException;
import application.model.Client;
import application.service.ClientService;
import application.view.EntityDialog;

import java.util.Scanner;

public class DeleteClientDialog implements EntityDialog<Client> {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;

    public DeleteClientDialog(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client input() throws NonExistingEntityException {
        clientService.getAll().forEach(System.out::println);
        Client client = null;
        while (client == null) {
            System.out.println("ID на клиент: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            client =  clientService.getById(id);
            if(client==null){
                System.out.println("Клиент с такова ID не съществува!");
            }
        }
        return client;
    }
}
