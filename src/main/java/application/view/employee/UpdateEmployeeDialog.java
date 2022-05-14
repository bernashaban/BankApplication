package application.view.employee;


import application.exception.NonExistingEntityException;
import application.model.Employee;
import application.service.EmployeeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class UpdateEmployeeDialog implements EntityDialog<Employee> {
    public static Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService;

    public UpdateEmployeeDialog(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee input() throws NonExistingEntityException {
        Employee employee = null;
        while (employee == null) {
            System.out.println("ID на служител:");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            employee = employeeService.getById(id);
            if (employee == null) {
                System.out.println("Служител с такова ID не съществува!");
            }
        }
        employee.setUsername(null);
        while (employee.getUsername() == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Потребителското име трябва да бъде между 2 и 15 символа.\"");
            } else {
                employee.setUsername(answer);
            }
        }
        employee.setPassword(null);
        while (employee.getPassword() == null) {
            System.out.println("Парола: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Паролата трябва да бъде между 2 и 15 символа.\"");
            } else {
                employee.setPassword(answer);
            }
        }
        employee.setName(null);
        while (employee.getName() == null) {
            System.out.println("Име и фамилия: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Името и фамилията трябва да е между 2 и 20 символа.\"");
            } else {
                employee.setName(answer);
            }
        }
        employee.setPhone(null);
        while (employee.getPhone() == null) {
            System.out.println("Телефон: ");
            var answer = scanner.nextLine();
            if (answer.length() != 10) {
                System.out.println("Грешка: Телефона трябва да бъде точно 10 символа\"");
            } else {
                employee.setPhone(answer);
            }
        }
        return employee;
    }
}
