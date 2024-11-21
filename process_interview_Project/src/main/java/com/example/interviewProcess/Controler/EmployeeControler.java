package com.example.interviewProcess.Controler;

import com.example.interviewProcess.Common.ApiResponce;
import com.example.interviewProcess.Model.Employee;
import com.example.interviewProcess.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeControler {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employee Createmployee(@RequestBody Employee employee){
        System.out.println("ControlerEmployee"+employee);
        Employee datas=employeeService.CreatEmployee(employee);
        return datas;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponce> Login(@RequestBody Employee employee){
       ApiResponce token= employeeService.Login(employee);
        if(token.getData()!=null){
            return ResponseEntity.ok(token);
        }else{
            ApiResponce errorResponce=new ApiResponce();
            errorResponce.setStatus(HttpStatus.UNAUTHORIZED.value());
            errorResponce.setError("Invalid Email and Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponce);
        }
    }

    @GetMapping("/{id}")
    public  Employee employeeInformation(@PathVariable Long id){
        return  employeeService.employeeInfo(id);
    }
}
