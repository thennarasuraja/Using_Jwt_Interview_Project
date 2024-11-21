import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InterviewProcessService } from '../Service/interview-process.service';
import { Employee } from '../Classes/employee';

@Component({
  selector: 'app-apply-candidates',
  standalone: true,
  imports: [ FormsModule,CommonModule],
  templateUrl: './apply-candidates.component.html',
  styleUrl: './apply-candidates.component.css'
})
export class ApplyCandidatesComponent implements OnInit{

  constructor(private service:InterviewProcessService){}

  @Input() employeeID!:number

  allCandidates:any[]=[]
  employee!:Employee;

  loading: boolean = true;

  ngOnInit(): void {
    console.log("addCandidate Employee ID: ",this.employeeID)
    this.getAllCandidates();
    this.getEmployeedata();
    // if(this.employeeID){
    //   this.getEmployeedata(this.employeeID);
    // }
  }

  getAllCandidates(){
       this.service.getAllCandidates().subscribe((resPonce)=>{
        console.log("resPonce",resPonce)
            this.allCandidates=resPonce;
       })
  }
  getEmployeedata(){
    this.service.employeeDetailes(this.employeeID).subscribe((responce)=>{
             console.log("responce",responce)
             this.employee=responce

             this.loading = false;
    }
  );
  }
}
