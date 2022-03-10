import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from './course';
import { CourseRegisterDTO, CourseRegisterDTO2 } from './model/course-register';

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
  
  public addCourseRegister(courseRegister: CourseRegisterDTO2): Observable<CourseRegisterDTO2>{
    const url = `${this.apiServiceUrl}/course/addCourseRegister`;
    return this.http.post<any>(url, courseRegister);
  }

  public getCourseInfo() {

    const urls = `${this.apiServiceUrl}/course/getEmployees`;

    return this.http.get<any>(urls).pipe(

     tap(receiveCourseI => console.log(`receiveCourseI = ${JSON.stringify(receiveCourseI)}`)),

     catchError(error => of([]))

   );

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

public UpdateCourse(data:any, id: number){
  return this.http.put<any>(`${this.apiServiceUrl}/course/getEmployees` + id, data);
}

public deleteCourse(id: number) {
  return this.http.delete<any>(`${this.apiServiceUrl}/course/delete` + id);
}



}
