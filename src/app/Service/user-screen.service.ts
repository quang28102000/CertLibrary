import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserScreenService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getInfomation(userId:string){
    const url = `${this.apiServiceUrl}/course/userProfile/${userId}`;
    //const url = `${this.apiServiceUrl}/course/userProfile`;

    // const url = `${this.apiServiceUrl}/course/userProfile`;
    // const url = `${this.apiServiceUrl}/ANV1`;
    return this.http
    .get<any>(url);
  }

}
