import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  public isDialogClosed = false;
  public idClicked!:string;

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getList():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/all`);
  }
  



  public getRegisteredCoursesByEmployee(): Observable<Course[]>{
    return this.http.get<Course[]>(`${this.apiServiceUrl}/course/registeredCoursesByEmployee`);
  }

  public getUnregisteredCoursesByEmployee(): Observable<Course[]>{
    return this.http.get<Course[]>(`${this.apiServiceUrl}/course/unregisteredCoursesByEmployee`);
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
