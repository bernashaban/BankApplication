package application.view.employeeMenuDialogs;

import application.controller.EmployeeController;
import application.model.Client;
import application.model.Employee;
import application.model.Position;
import application.service.ClientService;
import application.service.EmployeeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class UpdateDataDialogEmp implements EntityDialog<Employee> {

    public static Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService;
    private Employee employee;

    public UpdateDataDialogEmp(EmployeeService employeeService, Employee employee) {
        this.employeeService = employeeService;
        this.employee = employee;
    }

    @Override
    public Employee input() {
        employee.setUsername(null);
        while (employee.getUsername() == null) {
            System.out.println("Username: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Username should be between 2 and 15 characters long.");
            } else {
                employee.setUsername(answer);
            }
        }
        employee.setPassword(null);
        while (employee.getPassword() == null) {
            System.out.println("Password: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Password should be between 2 and 15 characters long.");
            } else {
                employee.setPassword(answer);
            }
        }
        employee.setName(null);
        while (employee.getName() == null) {
            System.out.println("Name: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Error: Name should be between 2 and 15 characters long.");
            } else {
                employee.setName(answer);
            }
        }
        employee.setPhone(null);
        while (employee.getPhone() == null) {
            System.out.println("Phone: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Error: Phone should be 10 characters long.");
            } else {
                employee.setPhone(answer);
            }
        }
        return employee;
    }
}
