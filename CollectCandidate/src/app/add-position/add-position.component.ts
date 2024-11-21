import { Component, Input, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Position } from '../Classes/position';
import { CommonModule } from '@angular/common';
import { InterviewProcessService } from '../Service/interview-process.service';
import { Employee } from '../Classes/employee';

@Component({
  selector: 'app-add-position',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './add-position.component.html',
  styleUrl: './add-position.component.css'
})
export class AddPositionComponent implements OnInit{

  constructor( private service:InterviewProcessService){}

  allPosition:Position[]=[]

  position:string=''

  employee!:Employee;
  
  loading: boolean = true;

  @Input() employeeID!:number

  ngOnInit(): void {
    this.getAllPositions()
    console.log("addposition Employee ID: ",this.employeeID)
    this.getEmployeedata()
    // if(this.employeeID){
    //   this.getEmployeedata(this.employeeID)
    // }
    
  }

  addPosition(position:NgForm){
     console.log(position.value);
     this.service.addPosition(this.position).subscribe((val)=>{
      console.log("createPosition",val);
      this.getAllPositions();
      position.resetForm()
      this.position=""
      this.getAllPositions()
     })
  }
  getAllPositions(){
    this.service.GetAllPositions().subscribe((position)=>{
      this.allPosition=position;
    })
  }
  deletePosition(id:number){
      this.service.deletePosition(id).subscribe((val)=>{
        console.log(val);
        this.getAllPositions();
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
