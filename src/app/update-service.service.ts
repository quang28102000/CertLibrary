import { Information } from './information';
import { environment } from './../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UpdateServiceService {
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

 
}
