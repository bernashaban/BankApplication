package application.view.employee;

import application.exception.NonExistingEntityException;
import application.model.Employee;
import application.service.EmployeeService;
import application.view.EntityDialog;

import java.util.Scanner;

public class SearchEmployeeByIdDialog implements EntityDialog<Employee> {
    public static Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService;

    public SearchEmployeeByIdDialog(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee input() throws NonExistingEntityException {
        Employee employee = null;
        while (employee == null) {
            System.out.println("ID на служител: ");
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
        return employee;
    }
}
