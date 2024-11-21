import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from '../Classes/employee';
import { Observable } from 'rxjs';
import { Position } from '../Classes/position';
import { Candidate } from '../Classes/candidate';
import { ApiResponce } from '../api-responce';


@Injectable({
  providedIn: 'root'
})
export class InterviewProcessService {
  constructor(private Http:HttpClient) { }

  LoginUrl="http://localhost:8080/employee/login"

  registerUrl="http://localhost:8080/employee/register"
  employeeIDUrl="http://localhost:8080/employee"

  creatpositionUrl="http://localhost:8080/position/creat"

  getAllPositionUrl="http://localhost:8080/position/getPositions"

  deletePostionUrl="http://localhost:8080/position/delete"

  submitCandidateDataUrl="http://localhost:8080/candidate/submit"
  getAllCandidateUrl="http://localhost:8080/candidate/allCandidates"


  //     employeee
  employeeLogin(employee:Employee) : Observable<ApiResponce<any>>{
    return  this.Http.post<ApiResponce<any>>(this.LoginUrl,employee);
  }

  employeeRegister(employeeData:Employee) : Observable<Employee>{
    return this.Http.post<Employee>(this.registerUrl,employeeData);
  }

  employeeDetailes(employeeId:number):Observable<Employee>{
    return this.Http.get<Employee>(this.employeeIDUrl+"/"+employeeId)
  }


  ///    position
  addPosition(positiondata:string):Observable<Position>{
    return this.Http.post<Position>(this.creatpositionUrl,positiondata);
  }

  GetAllPositions():Observable<Position[]>{
    return this.Http.get<Position[]>(this.getAllPositionUrl);
  }

  deletePosition(id:number):Observable<string>{
    return this.Http.delete<string>(this.deletePostionUrl+"/"+id);
  }

  //    candiddate
  submitCandidateData(formdata:FormData):Observable<any>{
     return this.Http.post<any>(this.submitCandidateDataUrl,formdata)
  }

  getAllCandidates():Observable<any[]>{
    return this.Http.get<any[]>(this.getAllCandidateUrl);
  }



}
