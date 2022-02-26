import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';
import { CourseDto } from './course-dto';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiServiceUrl}/course/all`);
  }

  public getCoursesDto(): Observable<CourseDto[]> {
    return this.http.get<CourseDto[]>(`${this.apiServiceUrl}/course/coursesDto`);
  }

}
