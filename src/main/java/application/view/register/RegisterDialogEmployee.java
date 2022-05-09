package application.view.register;

import application.exception.NonExistingEntityException;
import application.model.Employee;
import application.service.EmployeeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class RegisterDialogEmployee implements EntityDialog<Employee> {
    public static Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService;

    public RegisterDialogEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee input() throws NonExistingEntityException {
        Employee employee = new Employee();

        while (employee.getUsername() == null) {
            System.out.println("Потребителско име: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Потребителското име трябва да бъде между 2 и 15 символа.\"");
            } else {
                employee.setUsername(answer);
            }
        }
        while (employee.getPassword() == null) {
            System.out.println("Парола: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Паролата трябва да бъде между 2 и 15 символа.\"");
            } else {
                employee.setPassword(answer);
            }
        }
        while (employee.getName() == null) {
            System.out.println("Име и фамилия: ");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("Грешка: Името и фамилията трябва да е между 2 и 20 символа.\"");
            } else {
                employee.setName(answer);
            }
        }
        while (employee.getPhone() == null) {
            System.out.println("Телефон: ");
            var answer = scanner.nextLine();
            if (answer.length() !=10) {
                System.out.println("Грешка: Телефона трябва да бъде точно 10 символа\"");
            } else {
                employee.setPhone(answer);
            }
        }
        return employee;
    }
}
