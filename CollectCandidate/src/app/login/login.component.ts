import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { InterviewProcessService } from '../Service/interview-process.service';
import { Router } from '@angular/router';
import { Employee } from '../Classes/employee';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  registerForm:FormGroup

  constructor(private interviewservice:InterviewProcessService,private router:Router){
    this.registerForm=new FormGroup({
      employeeName:new FormControl("",[Validators.pattern('[a-zA-Z]*'),Validators.required]),
      email:new FormControl("",[Validators.required]),
      password:new FormControl("",Validators.required)
    })
  }

  isRegister:boolean=false;

  logInForm=new FormGroup({
    email:new FormControl("",[Validators.pattern('[a-zA-Z ]*'),Validators.required]),
    password:new FormControl("",Validators.required) 
  })

 
  loginFailed:boolean=false;

  formName:string='Log In Form';



  toLogIn(){
   console.log(this.logInForm.value)

   const getLogValue:any=this.logInForm.value;

   this.interviewservice.employeeLogin(getLogValue).subscribe((Responce)=>{
    console.log("Responce",Responce)
    if(Responce.data != null){

      const token=Responce.data.accesstoken;
      console.log("token: ",token)
      sessionStorage.setItem("access_token",token);

      const decodeToken=jwtDecode<any>(token)     
      console.log("decode : ",decodeToken);

     

      this.router.navigate(["employee",decodeToken.id]);
    }else{
         this.loginFailed=true;
    }
   })

  }
  toRegister(){
    this.isRegister=true;
    this.formName='Register Form';
  }
  toRegisterFun(){
    console.log(this.registerForm.value)
    this.interviewservice.employeeRegister(this.registerForm.value).subscribe((res)=>{
      if(res){
        console.log("responce",res)
        this.isRegister=false;
        this.formName='Log In Form';
      }
      else{
        console.log("something else")
      }
    })

  }



}
