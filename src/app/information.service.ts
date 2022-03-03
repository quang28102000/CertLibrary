import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Information } from './information';

@Injectable({
  providedIn: 'root',
})
export class InformationService {
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getInformation(): Observable<Information[]> {
    return this.http.get<Information[]>(
      `${this.apiServiceUrl}/information/all`
    );
  }
}
