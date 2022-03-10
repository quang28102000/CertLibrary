import { DataSource } from '@angular/cdk/table';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { CourseService } from '../course.service';
import { of } from 'rxjs';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { CourseDto } from '../model/course-dto';
import { Employee } from '../model/Employee';
import { Course } from '../course';
import { HomeService } from '../Service/home.service';

export interface DialogData {
  data: any[];
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  public totalLengthEmp: any; // paging
  p: number = 1; //paging

  public popup_title: any;
  public popup_data: any;
  public courses!: any[];
  public employees!: any[];

  public employeesInLast7Days!: any[];

  statistics!:  Map<Number, Employee[]>;
  coursesDto!: CourseDto[];

  constructor(public dialog: MatDialog,
    private router: Router,
    private homeService: HomeService,
    private courseService: CourseService) {}

  ngOnInit(): void {
    this.getUserHomePageStatistics();
    this.getCourses();
    this.totalLengthEmp = this.popup_data.length;
    
  }

  getCourses(){
    this.courseService.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list", res);
    })
  }

  SetPopUpData(num: any, title: string){
    this.popup_title = title
    if(num==5){
      this.popup_data = this.employeesInLast7Days
    }else{
      this.popup_data = this.statistics.get(num);
    }
  }


  rowClick(id: any, button: any){
    const app = document.getElementsByClassName("btn-close")[button] as HTMLElement;
    app?.click();
    this.router.navigate(['user-screen', id]);
  }




  public getUserHomePageStatistics(): void {
    // Map<status, Map<count, employees>>
    const employeesSubcribedCourse = new Map<Number,Employee[]>();

    this.homeService.getEmployees().subscribe(
      (employees: Employee[]) => {
        // đã đăng ký = chuwa ht + ddax ht
        employeesSubcribedCourse.set(
          1, employees.filter(emp => Number(emp.status) === 1)
        );

        // chưa đăng ký
        employeesSubcribedCourse.set(
          2,
            employees.filter(emp => Number(emp.status) === 2)
        );
        
        // đã hoàn thành
        employeesSubcribedCourse.set(
          3,employees.filter(emp => Number(emp.status) === 3)
        );

        // chưa hoàn thành
        employeesSubcribedCourse.set(
          4, employees.filter(emp => Number(emp.status) === 4)
        );

        //console.log(...[...employeesSubcribedCourse.entries()]);
        this.employees = employees;
          this.statistics = employeesSubcribedCourse;
          console.log(this.statistics);
      }
    )

    this.homeService.getEmployeesInLast7Days().subscribe(
      res => {
        this.employeesInLast7Days = res;
        console.log( 'EmployeesInLast7Days', res);
      }
    )
  }

  
    
}
