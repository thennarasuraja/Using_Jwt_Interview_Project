package com.example.interviewProcess.Service;

import com.example.interviewProcess.Common.ApiResponce;
import com.example.interviewProcess.Model.Employee;
import com.example.interviewProcess.Repositary.EmployeeRepositary;
import com.example.interviewProcess.Utill.JwtUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    private EmployeeRepositary employeeRepositary;

    @Autowired
    private JwtUtill jwtUtill;

    @Override
    public Employee CreatEmployee(Employee employee) {
        System.out.println("Employee"+employee);
        Employee employee1=new Employee();
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setEmail(employee.getEmail());
        employee1.setPassword(employee.getPassword());
        employee1.setActive(true);
        employee1.setUsertype("NORMAL");
        return employeeRepositary.save(employee1);

    }

    @Override
    public ApiResponce Login(Employee employee) {
        System.out.println(employee.getEmail());
        System.out.println(employee.getPassword());
        ApiResponce apiResponce=new ApiResponce();
        Employee foundemployee=employeeRepositary.findByEmailAndPassword(employee.getEmail(), employee.getPassword());
        System.out.println("foundemployee"+foundemployee);
        if(foundemployee==null){
            apiResponce.setData("Log In Failed");
            apiResponce.setError(HttpStatus.BAD_REQUEST);
            return apiResponce;
        }
        String token= jwtUtill.generateToken(foundemployee);

        Map<String, Object> data=new HashMap<>();
        data.put("accesstoken",token);
//        data.put("id",foundemployee.getId());
        apiResponce.setData(data);
        apiResponce.setStatus(HttpStatus.OK.value());
        return apiResponce;

    }

    @Override
    public Employee employeeInfo(Long id) {
        Employee employeedetail=employeeRepositary.findById(id).get();
        return employeedetail;
    }
}
