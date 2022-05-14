package application.controller.employeeManage;

import application.service.EmployeeService;
import application.view.Menu;
import application.view.employee.DeleteEmployeeDialog;
import application.view.employee.SearchEmployeeByIdDialog;
import application.view.employee.UpdateEmployeeDialog;
import application.view.register.RegisterDialogEmployee;

import java.util.List;
import java.util.Scanner;

public class EmpEmployeeController {
    public static Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService;

    public EmpEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void init() {
        var menu = new Menu("Управление на служители", List.of(
                new Menu.Option("Виж всички служители", () -> {
                    employeeService.getAll().forEach(System.out::println);
                    return "";
                }),
                new Menu.Option("Регистрирай служител", () -> {
                    var created = new RegisterDialogEmployee(employeeService).input();
                    employeeService.add(created);
                    return "Служителят е регистриран успешно!";
                }),
                new Menu.Option("Актуализирай служител", () -> {
                    var updating = new UpdateEmployeeDialog(employeeService).input();
                    employeeService.update(updating);
                    return "Служителят е актуализиран успшено!";
                }),
                new Menu.Option("Търси служител по ID", () -> {
                    var employee = new SearchEmployeeByIdDialog(employeeService).input();
                    System.out.println(employee);
                    return "";
                }),
                new Menu.Option("Изтрий служител", () -> {
                    var deleted = new DeleteEmployeeDialog(employeeService).input();
                    employeeService.delete(deleted.getId());
                    return "Служителят е изтрит успешно!";
                })
        ));
        menu.show();
    }
}
