package com.example.interviewProcess.Repositary;

import com.example.interviewProcess.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee,Long> {

    Employee findByEmailAndPassword(String email, String password);
}
