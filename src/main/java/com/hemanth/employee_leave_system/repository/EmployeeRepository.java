package com.hemanth.employee_leave_system.repository;

import com.hemanth.employee_leave_system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
