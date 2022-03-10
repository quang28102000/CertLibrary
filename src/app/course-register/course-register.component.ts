import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { EmployeeService } from '../Service/employee.service';
import { CourseRegisterDTO } from '../model/course-register';
import { map, Observable, startWith } from 'rxjs';
import { FormControl } from '@angular/forms';
import { Employee } from '../model/Employee';


@Component({
  selector: 'app-course-register',
  templateUrl: './course-register.component.html',
  styleUrls: ['./course-register.component.css']
})
export class CourseRegisterComponent implements OnInit {

  public filterCategory: any;
  //list 
  public employees!: Employee[];
  public courses!: any[];

  //
  public employee_name: any;
  public employee_info!: string;
  public platform!: any;
  public status!: any;
  public start!: any;
  public totalTime!: any;
  public course_info!: string;
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

  // SelectEmployee(){
  //   console.log('id',1);
  //   this.employeeId == 1;
  // }




  Add(){
    var startDate = new Date(this.start).toLocaleDateString();
    var endDate = new Date(this.end).toLocaleDateString();

    var course_name = this.course_info.split(' ` ')[1];
    var course_id = this.course_info.split(' ` ')[0];

    var employee_id = this.employee_info.split(' ` ')[0];
    var employee_name = this.employee_info.split(' ` ')[1];

    // console.log(course_id + course_name);
    const addNew: CourseRegisterDTO = {
      employee_id: employee_id,
      employee_name: employee_name,
      course_id: course_id,
      course_name: course_name,
      platform: this.platform,
      status: this.status,
      startDate: startDate,
      endDate: endDate,
      certLink: this.certLink,
      totalTime: this.totalTime
    };

    // console.log(this.employeeId + " " + this.platform 
    //             + " " + this.status + " " + startDate
    //             + " " + endDate + " " + this.certLink
    //             + " " + this.totalTime + " " + this.course_name);

    console.log('add',addNew);
    this.courseService.addCourseRegister(addNew).subscribe((data)=> console.log('send',data));
            
  }

}


