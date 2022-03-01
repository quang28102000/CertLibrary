import { Component, Inject, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { CourseService } from '../course.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  filterCategory = 'All';
  filterPlatform = 'All';
  courses:any;

  searchTxt: string = '';

  course_category: any;
  constructor(private course: CourseService,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.GetAllCourse();
  }


  GetAllCourse(){
    this.course.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list b3", res);
    })
  }

  showCategory(category: string) {
    const dkAll = this.filterCategory === 'All';
    const dkCate = this.filterCategory === 'this.courses.selector.component.categories';
    return dkAll || dkCate;
  }

  showPlatform(platform: string) {
    const dkAll = this.filterCategory === 'All';
    const dkPlat = this.filterPlatform === 'this.platform';
    return dkAll || dkPlat;
  }

  courseDetail(course:any){
    this.dialog.open(DialogCourse, {
      data: course
    });
  }


}


@Component({
  selector: 'dialog-course',
  templateUrl: 'dialogCourse.html',
  styleUrls: ['dialogCourse.css']
})
export class DialogCourse {
  public employees!: any[];
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router,
    public dialog: MatDialog) {
      
  }

}
