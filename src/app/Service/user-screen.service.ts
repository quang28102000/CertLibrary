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

  
  public getCourseR() {
    const urls = `${this.apiServiceUrl}/course/getCourseEmployees`;
    //const urls = `${this.url}/${courseId}`;
    return this.http.get<any>(urls).pipe(
      tap(receiveCourseR => console.log(`receiveCourseR = ${JSON.stringify(receiveCourseR)}`)),
      catchError(error => of([]))
    );
  }

  

  

}
