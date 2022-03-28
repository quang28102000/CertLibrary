import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import {MatDialog} from '@angular/material/dialog';
import { Information } from '../model/information';
import { InformationService } from 'src/app/Service/information.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {
  public infor!: Information[];
  public editInfor!: Information;
  public deleteInfor!: Information;

  //paging
  p: number = 1;
  count: number = 4;
  constructor(
    private InformationService: InformationService,
    private courseService : CourseService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getCourseInformation();
    this.getInformationDto();
  }
  public getInformationDto(): void {
    this.InformationService.getInformation().subscribe(
      (res) => {
        this.infor = res;
        console.log('list: ', res);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  public onUpdateInfor(Infor: Information): void {
    this.InformationService.updateInformation(Infor).subscribe(
      (response: Information) => {
        console.log(response);
        this.getInformationDto();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // public onDeleteInfor(informationId: number): void {
  //   this.InformationService.deleteInformation(informationId).subscribe(
  //     (response: void) => {
  //       console.log(response);
  //       this.getInformationDto();
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

  public onDeleteInfor(Infor: Information): void {
    this.InformationService.deleteInformation(Infor).subscribe(
      (response: Information) => {
        console.log(response);
        this.getInformationDto();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(Infor: Information, mode: string): void {
    
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    
    // alert("open model: " + mode );
    if (mode === 'edit') {
      this.editInfor = Infor;
      button.setAttribute('data-target', '#updateInforModal');
    }
    if (mode === 'delete') {
      this.deleteInfor = Infor;
      button.setAttribute('data-target', '#deleteInforModal');
    }
    container!.appendChild(button);
    button.click();
  }
  cInfo : any;
  getCourseInformation(){
    this.courseService.getCourseInfo().subscribe(
      (ci) => this.cInfo = ci
    )
  }

  onEdit() {
    this.dialog.open(DialogUpdateComponent);
  }

}

@Component({
  selector: 'dialog-update',
  templateUrl: './dialog-update.html',
  styleUrls: ['./dialog-update.css']
})
export class DialogUpdateComponent implements OnInit {

  constructor( ) { }

  ngOnInit(): void {

  }



}
