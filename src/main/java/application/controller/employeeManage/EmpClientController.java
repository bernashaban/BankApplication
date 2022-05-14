package application.controller.employeeManage;

import application.service.ClientService;
import application.view.Menu;
import application.view.client.DeleteClientDialog;
import application.view.client.SearchClientByIdDialog;
import application.view.client.UpdateClientDialog;
import application.view.register.RegisterDialogClient;

import java.util.List;
import java.util.Scanner;

public class EmpClientController {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;

    public EmpClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void init() {
        var menu = new Menu("Управление на клиенти", List.of(
                new Menu.Option("Виж всички клиенти", () -> {
                    clientService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Регистрирай клиент", () -> {
                    var created = new RegisterDialogClient().input();
                    clientService.add(created);
                    return "Клиентът е регистриран успешно!";
                }),
                new Menu.Option("Актуализирай клиент", () -> {
                    var updating = new UpdateClientDialog(clientService).input();
                    clientService.update(updating);
                    return "Клиентът е актуализиран успшено!";
                }),
                new Menu.Option("Търси клиент по ID", () -> {
                    var client = new SearchClientByIdDialog(clientService).input();
                    System.out.println(client);
                    return "";
                }),
                new Menu.Option("Изтрий клиент", () -> {
                    var deleted = new DeleteClientDialog(clientService).input();
                    clientService.delete(deleted.getId());
                    return "Клиентът е изтрит успешно!";
                })
        ));
        menu.show();
    }
}
