import { CourseRegister } from './course-register';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CourseServiceService {
  private apiServiceUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  // public addCourseRegister(CourseRegister: CourseRegister): Observable<CourseRegister> {
  //   // return this.http.post<any>(
  //   //   `${this.apiServiceUrl}/course/addCourseRegister`
  //   // );

  //   return this.http.post<any>(
  //     `${this.apiServiceUrl}/course/addCourseRegister`, CourseRegister
  //   );
  // }

  public addCourseRegister(CourseRegister: CourseRegister): Observable<CourseRegister> {
    return this.http.post<CourseRegister>(`${this.apiServiceUrl}/course/addCourseRegister`, CourseRegister);
  }
}
