package com.hemanth.employee_leave_system.controller;

import com.hemanth.employee_leave_system.dto.LeaveStatusDTO;
import com.hemanth.employee_leave_system.entity.Employee;
import com.hemanth.employee_leave_system.entity.LeaveRequest;
import com.hemanth.employee_leave_system.service.EmployeeService;
import com.hemanth.employee_leave_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeService employeeService;

    // Get all leave requests
    @GetMapping("/all-leaves")
    public ResponseEntity<List<LeaveRequest>> getAllLeaves() {
        List<LeaveRequest> leaves = leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }

    // Get pending leaves
    @GetMapping("/pending-leaves")
    public ResponseEntity<List<LeaveRequest>> getPendingLeaves() {
        List<LeaveRequest> leaves = leaveService.getPendingLeaves();
        return ResponseEntity.ok(leaves);
    }

    // Approve or reject leave
    @PutMapping("/update-leave-status")
    public ResponseEntity<?> updateLeaveStatus(@RequestBody LeaveStatusDTO dto) {
        LeaveRequest leave = leaveService.updateLeaveStatus(dto);
        return ResponseEntity.ok("Leave status updated to: " + leave.getStatus());
    }

    // Get all employees
    @GetMapping("/all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
