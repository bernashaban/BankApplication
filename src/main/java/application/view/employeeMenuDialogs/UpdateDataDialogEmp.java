package application.view.employeeMenuDialogs;

import application.model.Employee;
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
