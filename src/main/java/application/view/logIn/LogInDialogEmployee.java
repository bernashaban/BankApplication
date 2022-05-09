package application.view.logIn;

import application.exception.NonExistingEntityException;

import application.model.Client;
import application.model.Employee;
import application.service.EmployeeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class LogInDialogEmployee implements EntityDialog<Employee> {
    public static Scanner scanner = new Scanner(System.in);
    public EmployeeService employeeService;

    public LogInDialogEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee input() throws NonExistingEntityException {
        Employee employee = null;
        while (employee == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            try {
                employee = employeeService.findEmployeeByUsername(answer);
            } catch (NullPointerException e) {
                System.out.println("Служителят не съществува.");
            }
            if (employee != null) {
                System.out.println("Парола:");
                var pass = scanner.nextLine();
                while (true) {
                    if (pass.equals(employee.getPassword())) {
                        return employee;
                    }
                    System.out.println("Грешна парола. Опитайте отново.");
                    pass = scanner.nextLine();
                }
            } else {
                System.out.println("Грешка: Служител с потребителско име: '" + answer + "' не съществува.");
            }
        }
        System.out.println("Неуспешен вход.");
        return null;
    }
}
