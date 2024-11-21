import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
// import { Candidate } from '../Classes/candidate';
import { InterviewProcessService } from '../Service/interview-process.service';
import { Employee } from '../Classes/employee';

@Component({
  selector: 'app-add-candidates',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './add-candidates.component.html',
  styleUrl: './add-candidates.component.css'
})
export class AddCandidatesComponent implements OnInit{

  userForm!:FormGroup;
  positions:any[]=[];
  employee!:Employee;
  loading: boolean = true;

  fileVal:File | null=null
  @Input() employeeID!:number
  constructor (private service:InterviewProcessService){

    this.userForm= new FormGroup({
      candidateName:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]),
      email:new FormControl('',Validators.required),
      interviewerName:new FormControl('',Validators.required),
      contact:new FormControl('',[Validators.required,Validators.pattern('[0-9]{10}')]),
      position:new FormControl('',Validators.required),
      experience:new FormControl('',Validators.required),
      location:new FormControl('',Validators.required),
      resume:new FormControl('',Validators.required)
    })
  
  }
  

  
  ngOnInit(): void {
     this.getpositions();
     console.log("addCandidate Employee ID: ",this.employeeID)
    this.getEmployeedata()
    //  this.userForm.patchValue({
    //   interviewerName:this.employee.employeeName
    //  })
  }
  Onchange(event: any): void {
    const file = event.target.files[0];
    console.log("Filename:", file.name);
    this.fileVal = file;
    console.log("File Value:", this.fileVal);
  }
  onSubmit(){
    console.log(this.userForm)
    // if(this.userForm.valid){
      const formdata=new FormData();
      formdata.append("candidateName",this.userForm.controls['candidateName'].value);
      formdata.append("email",this.userForm.controls['email'].value)
      formdata.append("interviewerName",this.userForm.controls['interviewerName'].value)
      formdata.append("contact",this.userForm.controls['contact'].value)
      formdata.append("position",this.userForm.controls['position'].value)
      formdata.append("experience",this.userForm.controls['experience'].value)
      formdata.append("location",this.userForm.controls['location'].value)

      if(this.fileVal){
        formdata.append("resume",this.fileVal,this.fileVal.name)
      }else{
        console.log("no resume file selected")
      }
      
      formdata.forEach((value,key)=>{
        console.log(`${key}: ${value}`)
      })

      this.service.submitCandidateData(formdata).subscribe((responce)=>{
        console.log("Responce: ",responce)
        this.userForm.reset();
      })
    // }

  }

  getEmployeedata(){
    this.service.employeeDetailes(this.employeeID).subscribe((responce)=>{
             console.log("responce",responce)
             this.employee=responce

             this.loading = false;
    }
  );
  }
 
  getpositions(){
    this.service.GetAllPositions().subscribe((val)=>{
      this.positions=val;
      console.log(this.positions)
    })
  }

  

}
