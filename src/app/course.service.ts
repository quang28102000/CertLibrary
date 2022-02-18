import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiServiceUrl}/course/all`);
  }

  public getNumberOfCourses(): Observable<Number> {
    return this.http.get<Number>(`${this.apiServiceUrl}/course/numberOfCourses`);
  }

}
