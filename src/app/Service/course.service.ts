import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../course';
import { CourseRegisterDTO, CourseRegisterDTO2, CourseRegisterDTO3 } from '../model/course-register';
import { CourseDelete, CourseDeleteDto } from '../model/course-delete';
import { courseCreate } from '../model/courseC';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  public isDialogClosed = false;
  public idClicked!:string;

  public courseCategory: String[] = [];
  public coursePlatform: String[] = [];


  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }


  public getAllCourse():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/coursesDto`);
  }

  public getAll(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/getCourseEmployees`);
  }

  public getSkills(){
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/courseSkills`);
  }
  
  public addCourseRegister(courseRegister: CourseRegisterDTO3): Observable<CourseRegisterDTO3>{
    console.log('receive', courseRegister);
    const url = `${this.apiServiceUrl}/course/addCourseRegister`;
    return this.http.post<any>(url, courseRegister);
  }


 public getCourseInfo(): Observable<CourseDeleteDto[]> {
  return this.http.get<CourseDeleteDto[]>(`${this.apiServiceUrl}/course/getEmployees`);
}



 public getCourseCategory(): String[]{
   this.getAllCourse().subscribe((data)=>{
    data.forEach(element => {
      this.courseCategory.push(element.category);
    });
   })

    console.log('serbice1',this.courseCategory)
   
    this.courseCategory = this.courseCategory.filter((v, i, a) => a.indexOf(v) === i);
    return this.courseCategory;
 }


 public searchCourse(typedString: string): Observable<any> {
  if (!typedString.trim()) {
    return of([]);
  }
  return this.http.get<any>(`${this.apiServiceUrl}?tittle_like=${typedString}`).pipe(
    tap(foundCourses => console.log(`found courses = ${JSON.stringify(foundCourses)}`)),
    catchError(error => of(null))
  );
}


  public updateCourse(data: any): Observable<any>{
    console.log('receive', data);
    return this.http.put<any>(`${this.apiServiceUrl}/course/updateCourse`, data);
  }



  


  public courseCreate(cc: courseCreate): Observable<courseCreate>{
    console.log('receive', cc);
    const url = `${this.apiServiceUrl}/course/addCourse`;
    return this.http.post<any>(url, cc);
  }




}
