import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';
import { CourseRegisterDTO } from './model/course-register';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  public isDialogClosed = false;
  public idClicked!:string;

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getList():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/coursesDto`);
  }

  public getInformationTittle():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiServiceUrl}/course`);
  }
  
  public addCourseRegister(courseRegister: CourseRegisterDTO): Observable<CourseRegisterDTO>{
    const url = `${this.apiServiceUrl}/course/addCourseRegister`;
    return this.http.post<any>(url, courseRegister);
  }

  public getCourseInfo() {

    const urls = `${this.apiServiceUrl}/course/coursesHomePageDto`;

    return this.http.get<any>(urls).pipe(

     tap(receiveCourseI => console.log(`receiveCourseI = ${JSON.stringify(receiveCourseI)}`)),

     catchError(error => of([]))

   );

 }



}
