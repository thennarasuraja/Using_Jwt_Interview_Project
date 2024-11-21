import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApplyCandidatesComponent } from "../apply-candidates/apply-candidates.component";
import { AddCandidatesComponent } from "../add-candidates/add-candidates.component";
import { AddPositionComponent } from "../add-position/add-position.component";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adminpage',
  standalone: true,
  imports: [CommonModule, FormsModule, ApplyCandidatesComponent, AddCandidatesComponent, AddPositionComponent],
  templateUrl: './adminpage.component.html',
  styleUrl: './adminpage.component.css'
})
export class AdminpageComponent implements OnInit{

  isPositionVisible:boolean = false;

  currentComponent:String="applyCandidates";

  userEmployeeId!:number

  constructor( private route:ActivatedRoute,private router:Router){}
  ngOnInit(): void {
    this.route.paramMap.subscribe((params)=>{
      console.log("params",params)
      this.userEmployeeId=Number(params.get('id'))
      this.checkAuth()
    })
  }

  checkAuth() {
    const token = sessionStorage.getItem('access_token');
    if (!token) {
      this.router.navigate(['/']);
    }
  }
 
  positionVisibility() {
    this.isPositionVisible = !this.isPositionVisible;
    this.toNavigate('addposition')
  }
  toNavigate(value:string){
    if(value!='addposition'){
      this.isPositionVisible=false
    }
     this.currentComponent=value;
     console.log("current component: ",this.currentComponent)
  }

}
