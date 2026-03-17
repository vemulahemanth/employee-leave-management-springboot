package com.hemanth.employee_leave_system.service;

import com.hemanth.employee_leave_system.dto.LeaveRequestDTO;
import com.hemanth.employee_leave_system.dto.LeaveStatusDTO;
import com.hemanth.employee_leave_system.entity.Employee;
import com.hemanth.employee_leave_system.entity.LeaveRequest;
import com.hemanth.employee_leave_system.repository.EmployeeRepository;
import com.hemanth.employee_leave_system.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Apply for leave
    public LeaveRequest applyLeave(LeaveRequestDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setLeaveType(LeaveRequest.LeaveType.valueOf(dto.getLeaveType().toUpperCase()));
        leave.setFromDate(dto.getFromDate());
        leave.setToDate(dto.getToDate());
        leave.setReason(dto.getReason());
        leave.setStatus(LeaveRequest.LeaveStatus.PENDING);

        return leaveRequestRepository.save(leave);
    }

    // Get leaves by employee
    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    // Get all leaves (admin)
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    // Get pending leaves (admin)
    public List<LeaveRequest> getPendingLeaves() {
        return leaveRequestRepository.findByStatus(LeaveRequest.LeaveStatus.PENDING);
    }

    // Update leave status (admin)
    public LeaveRequest updateLeaveStatus(LeaveStatusDTO dto) {
        LeaveRequest leave = leaveRequestRepository.findById(dto.getLeaveId())
                .orElseThrow(() -> new RuntimeException("Leave request not found!"));

        leave.setStatus(LeaveRequest.LeaveStatus.valueOf(dto.getStatus().toUpperCase()));
        return leaveRequestRepository.save(leave);
    }

    // Cancel leave (employee)
    public void cancelLeave(Long leaveId) {
        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found!"));

        if (!leave.getStatus().equals(LeaveRequest.LeaveStatus.PENDING)) {
            throw new RuntimeException("Only pending leaves can be cancelled!");
        }
        leaveRequestRepository.delete(leave);
    }
}
