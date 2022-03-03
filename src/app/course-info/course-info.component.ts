import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {

  constructor(
    private courseService : CourseService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getCourseInformation();
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
