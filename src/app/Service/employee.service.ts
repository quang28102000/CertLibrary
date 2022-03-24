import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAll(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/getEmployees`);
  }
  public getAll2(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/getEmployeesInfo`);
  }
}
