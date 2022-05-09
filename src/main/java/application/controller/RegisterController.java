package application.controller;

import application.service.ClientService;
import application.service.EmployeeService;
import application.service.PositionService;
import application.view.*;
import application.view.register.RegisterDialogClient;
import application.view.register.RegisterDialogEmployee;

import java.util.List;
import java.util.Scanner;

public class RegisterController {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;
    private EmployeeService employeeService;
    private PositionService positionService;

    public RegisterController(ClientService clientService, EmployeeService employeeService, PositionService positionService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    public void init() {
        var menu = new Menu("Регистрация", List.of(
                new Menu.Option("Регистрация като клиент", () -> {
                    var client = new RegisterDialogClient(clientService).input();
                    var created =  clientService.add(client);
                    return String.format("Клиент с ID:'%s', потребителско име: '%s' се регистрира успешно.", created.getId(), created.getUsername());

                }),
                new Menu.Option("Регистрация като служител", () -> {
                    var employee = new RegisterDialogEmployee(employeeService).input();
                    employee.setPosition(positionService.getById(1));
                    var created =  employeeService.add(employee);
                    return String.format("Служител с ID:'%s', потребителско име: '%s' се регистрира успешно.", created.getId(), created.getUsername());
                })
        ));
        menu.show();
    }
}
