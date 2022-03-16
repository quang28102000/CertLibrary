import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserScreenService {

  private apiServiceUrl = environment.apiBaseUrl;
  private url = 'http://localhost:3000/CourseRecent';

  constructor(private http: HttpClient) { }

  public getInfomation(userId:string){
    const url = `${this.apiServiceUrl}/course/userProfile/${userId}`;
    //const url = `${this.apiServiceUrl}/course/userProfile`;

    // const url = `${this.apiServiceUrl}/course/userProfile`;
    // const url = `${this.apiServiceUrl}/ANV1`;
    return this.http
    .get<any>(url);
  }
  public getCourseR() {
    const urls = `${this.apiServiceUrl}/course/getEmployees`;
    //const urls = `${this.url}/${courseId}`;
    return this.http.get<any>(urls).pipe(
      tap(receiveCourseR => console.log(`receiveCourseR = ${JSON.stringify(receiveCourseR)}`)),
      catchError(error => of([]))
    );
  }

  getCertificate(){
    const urls = `${this.apiServiceUrl}/course/getEmployees`;
    return this.http.get<any>(urls).pipe(
      tap(cert => console.log(`cert = ${JSON.stringify(cert)}`)),
      catchError(error => of([]))
    );
  }

  getSkills(){
    const urls = `${this.apiServiceUrl}/course/coursesDto`;
    return this.http.get<any>(urls).pipe(
      tap(skill => console.log(`skill = ${JSON.stringify(skill)}`)),
      catchError(error => of([]))
    );
  }

}
