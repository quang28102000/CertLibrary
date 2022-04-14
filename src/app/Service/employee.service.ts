import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from '../model/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }
  
  public getAllEmployeeInfo(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/employee/getEmployeesInfo`);
  }

  public getEmployeesInLast7Days(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServiceUrl}/employee/getEmployeesInLast7Days`); 
  }

  public getInfomation(userId:string){
    const url = `${this.apiServiceUrl}/employee/userProfile/${userId}`;
    //const url = `${this.apiServiceUrl}/course/userProfile`;

    // const url = `${this.apiServiceUrl}/course/userProfile`;
    // const url = `${this.apiServiceUrl}/ANV1`;
    return this.http
    .get<any>(url);
  }
}
