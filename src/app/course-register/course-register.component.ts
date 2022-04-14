import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeService } from '../Service/employee.service';
import { CourseRegisterDTO, CourseRegisterDTO2, CourseRegisterDTO3 } from '../model/course-register';
import { map, Observable, startWith } from 'rxjs';
import { FormControl } from '@angular/forms';
import { Employee } from '../model/Employee';
import * as $AB from 'jquery';
import * as bootstrap from "bootstrap";
import * as moment from 'moment';
import { CourseService } from '../Service/course.service';
import { CourseEmployeeService } from '../Service/course-employee.service';


@Component({
  selector: 'app-course-register',
  templateUrl: './course-register.component.html',
  styleUrls: ['./course-register.component.css']
})
export class CourseRegisterComponent implements OnInit {

  filterCategory: String[] = [];
  filterPlatform: String[] = [];
  //list 
  public employees!: Employee[];
  public courses!: any[];

  public emp!: any;
  //
  public platform!: any;
  public status!: any;
  public start!: any;
  public totalTime!: any;
  public end!: any;
  public certLink!: any;

  public categorySelected!: any;

  public courseSelected: any;


  constructor(public employeeService: EmployeeService,
    public courseEmployeeService: CourseEmployeeService,
    public courseService: CourseService) { }

  ngOnInit(): void {
    this.GetEmployees();
    this.GetCourse();
  }

  GetEmployees(){
    this.courseEmployeeService.getCourseEmployees().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })
  }
  
  GetCourse(){
    this.courseService.getAllCourse().subscribe(res => {
      this.courses = res;
      console.log("course-list", res);
      res.forEach(element => {
        this.filterCategory.push(element.category);
        this.filterPlatform.push(element.platform);
      });
      this.filterCategory = this.filterCategory.filter((v, i, a) => a.indexOf(v) === i);
      this.filterPlatform = this.filterPlatform.filter((v, i, a) => a.indexOf(v) === i);

      console.log('filter category', this.filterCategory);
      console.log('filter platform', this.filterPlatform);

    })
  }

  selectEmployee(item: any){
    console.log('id',item);

    const app = document.getElementsByClassName("btn-close")[0] as HTMLElement;
    app?.click();

    this.emp = item;
  }




  Add(){
    console.log((moment(this.start)).format('YYYY-MM-DD HH:mm:ss'));
    var startDate = (moment(this.start)).format('YYYY-MM-DD HH:mm:ss')
    var endDate = (moment(this.end)).format('YYYY-MM-DD HH:mm:ss')


    const addNew3: CourseRegisterDTO3 = {
      courseEmployee:{
        courseId: this.courseSelected.id,
        employeeId: Number(this.emp.employeeId),
        status: this.status,
        startDate: startDate,
        endDate: endDate,
        certLink: this.certLink,
        isDeleted: 0,
      }
    };
    console.log('add',addNew3);

    var flat = 0;
    this.employees.forEach(element => {
      if(element.employeeId == addNew3.courseEmployee.employeeId &&
        element.courseId == addNew3.courseEmployee.courseId){
          flat = 1;
        $('#notification').modal('show');
      }
    });

    if(flat==0){
      this.courseService.addCourseRegister(addNew3).subscribe((data)=>{
          console.log("send-data: ", data);
          $('#notification2').modal('show');
        }
      )
    }
  }

  optionClick(item: any){
    console.log(item);
  }

  selectCourse(course:any){
    console.log('a', course);
    this.categorySelected = course.category;
    this.platform = course.platform;
    this.totalTime = course.courseLength;
  }
}


