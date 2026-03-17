package com.hemanth.employee_leave_system.controller;

import com.hemanth.employee_leave_system.dto.LoginRequest;
import com.hemanth.employee_leave_system.dto.RegisterRequest;
import com.hemanth.employee_leave_system.entity.Employee;
import com.hemanth.employee_leave_system.security.JwtUtil;
import com.hemanth.employee_leave_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Employee employee = employeeService.registerEmployee(request);
        return ResponseEntity.ok("Employee registered successfully! ID: " + employee.getId());
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Employee employee = employeeService.getByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password!");
        }

        String token = jwtUtil.generateToken(employee.getEmail(), employee.getRole().name());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", employee.getRole());
        response.put("name", employee.getName());
        response.put("id", employee.getId());

        return ResponseEntity.ok(response);
    }
}
