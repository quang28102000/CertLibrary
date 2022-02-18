import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { environment } from 'src/environments/environment';
@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
    private apiServiceUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getRegisteredCoursesByEmployee(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/registeredCoursesByEmployee`);
    }

    public getUnregisteredCoursesByEmployee(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/unregisteredCoursesByEmployee`);
    }

    public getCompleteCoursesByEmployee(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/completeCourseByEmployee`);
    }

    public getIncompleteCoursesByEmployee(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/incompleteCourseByEmployee`);
    }

    public getNumberOfEmployeesInLast7days(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/numberOfEmployeesInLast7days`);
    }
 
    public getNumberOfCourses(): Observable<Number> {
        return this.http.get<Number>(`${this.apiServiceUrl}/course/numberOfCourses`);
    }

}