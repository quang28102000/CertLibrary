import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Information } from './information';
import { InformationService } from './information.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'fullstack';

  public infor: Information[];

  constructor( private InformationService: InformationService) { }

  ngOnInit(): void {
    this.getInformationDto();
  }

  public getInformationDto(): void {
    this.InformationService.getInformation().subscribe(
      (response: Information[]) => {
        this.infor = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
