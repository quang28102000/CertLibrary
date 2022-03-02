import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from '../model/Employee';
import { UserProfile } from '../model/user-profile';

@Injectable({
  providedIn: 'root'
})
export class HomeService {


  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServiceUrl}/course/getEmployees`);
}


  public getUserProfile(): Observable<UserProfile> {
      return this.http.get<UserProfile>(`${this.apiServiceUrl}/course/userProfile/4`); 
  }

  public getEmployeesInLast7Days(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServiceUrl}/course/getEmployeesInLast7Days`); 
  }
}
