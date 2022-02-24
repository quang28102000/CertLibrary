import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  
  public isDialogClosed = false;
  public idClicked!:string;


  public getRegisteredCoursesByEmployee(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/registeredCoursesByEmployee`);
  }

  public getUnregisteredCoursesByEmployee(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/unregisteredCoursesByEmployee`);
  }

  public getNumberOfEmployeesInLast7days(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/numberOfEmployeesInLast7days`);
  }

  public getNumberOfCourses(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/numberOfCourses`);
  }

  public getCompleteCourseByEmployee(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/completeCourseByEmployee`);
  }

  public getIncompleteCourseByEmployee(): Observable<number>{
    return this.http.get<number>(`${this.apiServiceUrl}/course/incompleteCourseByEmployee`);
  }
}
