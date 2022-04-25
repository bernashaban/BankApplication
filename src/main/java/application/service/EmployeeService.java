package application.service;

import application.dao.impl.EmployeeRepository;
import application.model.Employee;

import java.util.Collection;

public class EmployeeService implements Service<Employee>{
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee add(Employee entity) {
        return employeeRepository.create(entity);
    }

    @Override
    public Employee update(Employee entity) {
        return employeeRepository.update(entity);
    }

    @Override
    public Employee delete(Integer id) {
        return employeeRepository.deleteById(id);
    }
}
