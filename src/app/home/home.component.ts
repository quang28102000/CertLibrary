import { DataSource } from '@angular/cdk/table';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit, ViewChild, ViewChildren } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { CourseService } from '../course.service';
import { of } from 'rxjs';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { CourseDto } from '../model/course-dto';
import { Employee } from '../model/Employee';
import { Course } from '../course';
import { HomeService } from '../Service/home.service';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface DialogData {
  data: any[];
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  displayColumns: string[] = ['index', 'fullName', 'email','course', 'status', 'platform'];
  displayColumns2: string[] = ['index', 'fullName', 'email'];
  displayColumns3: string[] = ['index', 'name', 'platform', 'category', 'courseLength'];


  displayedColumns: string[] = [];


  dataSource!: MatTableDataSource<any>;
  dataSource2!: MatTableDataSource<any>;


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  @ViewChild(MatPaginator) paginator2!: MatPaginator;
  @ViewChild(MatSort) sort2!: MatSort;

  ngAfterViewInit() {
    this.dataSource2.paginator = this.paginator2;
    this.dataSource2.sort = this.sort2;
  }

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
      for (let index = 0; index < res.length; index++) {
        res[index].index = index+1;        
      }
      this.courses = res;
      console.log("course-list", res);
      this.dataSource2 = new MatTableDataSource(res);
      // this.dataSource2.paginator = this.paginator;
      // this.dataSource2.sort = this.sort;    
    })
  }

  SetPopUpData(num: any, title: string){
    this.popup_title = title
    if(num==5){
      this.popup_data = this.employeesInLast7Days
      this.displayedColumns = this.displayColumns
    }else if(num==-1){
      this.popup_data = this.courses;
      this.displayedColumns = this.displayColumns3;
    }
    else{
      this.popup_data = this.statistics.get(num);
      this.displayedColumns = this.displayColumns
    }
    // set stt
    for (let index = 0; index < this.popup_data.length; index++) {
      this.popup_data[index].index = index+1;        
    }
    this.dataSource = new MatTableDataSource(this.popup_data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  rowClick(item: any, button: any){
    const app = document.getElementsByClassName("btn-close")[button] as HTMLElement;
    app?.click();
    this.router.navigate(['user-screen', item.employeeId]);
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
