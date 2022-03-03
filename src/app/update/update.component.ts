import { InformationService } from './../information.service';
import { Information } from './../information';


import { Component, Input, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

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
