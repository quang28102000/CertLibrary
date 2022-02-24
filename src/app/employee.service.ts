import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { environment } from 'src/environments/environment';
import { Employee } from "./employee";
import { UserProfile } from "./user-profile";
@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
    private apiServiceUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getEmployees(): Observable<Employee[]> {
        return this.http.get<Employee[]>(`${this.apiServiceUrl}/course/getEmployees`);
    }


    public getUserProfile(): Observable<UserProfile> {
        return this.http.get<UserProfile>(`${this.apiServiceUrl}/course/userProfile`); 
    }

}