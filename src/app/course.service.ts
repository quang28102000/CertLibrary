import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';
import { CourseRegisterDTO, CourseRegisterDTO2, CourseRegisterDTO3 } from './model/course-register';
import { CourseDelete, CourseDeleteDto } from './model/course-delete';
import { courseCreate } from './model/courseC';

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


  public getList():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/coursesDto`);
  }

  public getAll(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/getEmployees`);
  }

  public getSkills(){
    return this.http.get<any[]>(`${this.apiServiceUrl}/course/courseSkills`);
  }
  
  public addCourseRegister(courseRegister: CourseRegisterDTO3): Observable<CourseRegisterDTO3>{
    console.log('receive', courseRegister);
    const url = `${this.apiServiceUrl}/course/addCourseRegister`;
    return this.http.post<any>(url, courseRegister);
  }

//   public getCourseInfo() {

//     const urls = `${this.apiServiceUrl}/course/getEmployees`;

//     return this.http.get<any>(urls).pipe(

//      tap(receiveCourseI => console.log(`receiveCourseI = ${JSON.stringify(receiveCourseI)}`)),

//      catchError(error => of([]))

//    );

//  }

 public getCourseInfo(): Observable<CourseDeleteDto[]> {
  return this.http.get<CourseDeleteDto[]>(`${this.apiServiceUrl}/course/getEmployees`);
}



 public getCourseCategory(): String[]{
   this.getList().subscribe((data)=>{
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

  public UpdateCourse(data: any): Observable<any>{
    console.log('receive', data);
    return this.http.put<any>(`${this.apiServiceUrl}/course/update`, data);
  }



  public deleteCourse(item: any):Observable<any>{

    const options = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
      }),
      body: item,
    };

    const url = `${this.apiServiceUrl}/course/delete`;
    console.log('receive', item);
    return this.http.delete<any>(url,options);
    // return this.http.request('DELETE', url, item)
  }

  public deleteMultipleCourse(item: any):Observable<any>{
    const options = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
      }),
      body: item,
    };

    const url = `${this.apiServiceUrl}/course/multipleDelete`;
    return this.http.delete<any>(url, options);

    // return this.http.request('DELETE', url, item)
  }

  public courseCreate(cc: courseCreate): Observable<courseCreate>{
    console.log('receive', cc);
    const url = `${this.apiServiceUrl}/course/addCourse`;
    return this.http.post<any>(url, cc);
  }



}
