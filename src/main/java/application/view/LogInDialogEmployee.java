package application.view;

import application.exception.NonExistingEntityException;

import application.model.Client;
import application.model.Employee;
import application.service.EmployeeService;

import java.util.Scanner;

public class LogInDialogEmployee implements EntityDialog<Employee>{
    public static Scanner scanner = new Scanner(System.in);
    public EmployeeService employeeService;

    public LogInDialogEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee input() throws NonExistingEntityException {
        Employee employee = null;
        while (employee == null) {
            System.out.println("Username: ");
            var answer = scanner.nextLine();
            try {
                employee = employeeService.findEmployeeByUsername(answer);
            } catch (NullPointerException e) {
                System.out.println("Employee does not exist.");
            }
            if (employee != null) {
                System.out.println("Password:");
                var pass = scanner.nextLine();
                while (true) {
                    if (pass.equals(employee.getPassword())) {
                        return employee;
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
