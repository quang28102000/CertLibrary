import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CourseService } from '../course.service';
import { EmployeeService } from '../Service/employee.service';
import { DateAdapter } from '@angular/material/core';
import { MAT_DATE_FORMATS } from '@angular/material/core';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import { Moment } from 'moment'; 
import * as _moment from 'moment';
import { CourseRegisterDTO } from '../model/course-register';
const moment = _moment; 

export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'DD-MM-YYY',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

@Component({
  selector: 'app-course-register',
  templateUrl: './course-register.component.html',
  styleUrls: ['./course-register.component.css'],
  providers:[
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ]
})
export class CourseRegisterComponent implements OnInit {

  //list 
  public employees!: any[];
  public courses!: any[];

  //
  public employeeId: any;
  public employeeName!: any;
  public platform!: any;
  public status!: any;
  public start!: any;
  public totalTime!: any;
  public course_name!: any;
  public end!: any;
  public certLink!: any;

  constructor(public employeeService: EmployeeService,
    public courseService: CourseService) { }

  ngOnInit(): void {
    this.GetEmployees();
    this.GetCourse();
  }

  GetEmployees(){
    this.employeeService.getAll().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })
  }
  
  GetCourse(){
    this.courseService.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list", res);
    })
  }

  SelectEmployee(){
    console.log('id',1);
    this.employeeId == 1;
  }



  Add(){
    var startDate = new Date(this.start).toLocaleDateString();
    var endDate = new Date(this.end).toLocaleDateString();

    // var addNew: CourseRegisterDTO = new CourseRegisterDTO()

    console.log(this.employeeId + " " + this.platform 
                + " " + this.status + " " + startDate
                + " " + endDate + " " + this.certLink
                + " " + this.totalTime + " " + this.course_name);

            
  }

}


