import { InformationCopy } from './../model/information-copy';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Information } from '../model/information';

@Injectable({
  providedIn: 'root'
})
export class InformationService {
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getInformation(): Observable<Information[]> {
    return this.http.get<Information[]>(
      `${this.apiServiceUrl}/information/all`
    );
  }

  public updateInformation(Infor: Information): Observable<Information> {
    return this.http.put<Information>(`${this.apiServiceUrl}/information/update`, Infor);
  }

  // public deleteInformation1(Infor: Information): Observable<Information> {
  //   return this.http.delete<Information>(`${this.apiServiceUrl}/information/deleteobj`, Infor);
  // }

  public deleteInformation(Infor: Information): Observable<Information> {
    return this.http.put<Information>(`${this.apiServiceUrl}/information/deleteobj`, Infor);
  }

  // public getInformationTittle(): Observable<InformationCopy[]> {
  //   return this.http.get<InformationCopy[]>(
  //     `${this.apiServiceUrl}/information/allName`
  //   );
  // }
}
