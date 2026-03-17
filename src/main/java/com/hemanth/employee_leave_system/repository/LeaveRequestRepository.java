package com.hemanth.employee_leave_system.repository;

import com.hemanth.employee_leave_system.entity.LeaveRequest;
import com.hemanth.employee_leave_system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(Employee employee);
    
    List<LeaveRequest> findByStatus(LeaveRequest.LeaveStatus status);
    
    List<LeaveRequest> findByEmployeeId(Long employeeId);
}
