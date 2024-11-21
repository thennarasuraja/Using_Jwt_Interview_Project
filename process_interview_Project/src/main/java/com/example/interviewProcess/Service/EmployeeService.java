package com.example.interviewProcess.Service;

import com.example.interviewProcess.Common.ApiResponce;
import com.example.interviewProcess.Model.Employee;

public interface EmployeeService {

    Employee CreatEmployee(Employee employee);

    ApiResponce Login(Employee employee);
    Employee employeeInfo(Long id);


}
