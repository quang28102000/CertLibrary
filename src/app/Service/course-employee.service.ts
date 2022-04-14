import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CourseDeleteDto } from '../model/course-delete';
import { Employee } from '../model/Employee';

@Injectable({
  providedIn: 'root'
})
export class CourseEmployeeService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCourseEmployees(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiServiceUrl}/course-employee/getCourseEmployees`);
  }

  public deleteCourse(item: any):Observable<any>{

    const options = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
      }),
      body: item,
    };

    const url = `${this.apiServiceUrl}/course-employee/delete`;
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

    const url = `${this.apiServiceUrl}/course-employee/multipleDelete`;
    return this.http.delete<any>(url, options);

    // return this.http.request('DELETE', url, item)
  }

  public update(data: any): Observable<any>{
    console.log('receive', data);
    return this.http.put<any>(`${this.apiServiceUrl}/course-employee/update`, data, {
          headers: new HttpHeaders({
            "Content-Type": "application/json",
          }),
          responseType: 'text' as 'json'
      });
  }

  getCertificate(){
    const urls = `${this.apiServiceUrl}/course/getCourseEmployees`;
    return this.http.get<any>(urls).pipe(
      tap(cert => console.log(`cert = ${JSON.stringify(cert)}`)),
      catchError(error => of([]))
    );
  }

  
}

