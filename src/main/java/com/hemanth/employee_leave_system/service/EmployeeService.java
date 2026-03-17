package com.hemanth.employee_leave_system.service;

import com.hemanth.employee_leave_system.dto.RegisterRequest;
import com.hemanth.employee_leave_system.entity.Employee;
import com.hemanth.employee_leave_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register new employee
    public Employee registerEmployee(RegisterRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(Employee.Role.valueOf(request.getRole().toUpperCase()));
        return employeeRepository.save(employee);
    }

    // Get employee by email
    public Optional<Employee> getByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Get employee by ID
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
