package com.hemanth.employee_leave_system.controller;

import com.hemanth.employee_leave_system.dto.LeaveRequestDTO;
import com.hemanth.employee_leave_system.entity.LeaveRequest;
import com.hemanth.employee_leave_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/apply-leave")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequestDTO dto) {
        LeaveRequest leave = leaveService.applyLeave(dto);
        return ResponseEntity.ok("Leave applied successfully! ID: " + leave.getId());
    }

    @GetMapping("/my-leaves/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getMyLeaves(@PathVariable Long employeeId) {
        List<LeaveRequest> leaves = leaveService.getLeavesByEmployee(employeeId);
        return ResponseEntity.ok(leaves);
    }

    @DeleteMapping("/cancel-leave/{leaveId}")
    public ResponseEntity<?> cancelLeave(@PathVariable Long leaveId) {
        leaveService.cancelLeave(leaveId);
        return ResponseEntity.ok("Leave cancelled successfully!");
    }
}