import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { EmployeeService } from '../Service/employee.service';
import { CourseRegisterDTO } from '../model/course-register';


@Component({
  selector: 'app-course-register',
  templateUrl: './course-register.component.html',
  styleUrls: ['./course-register.component.css']
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

    const addNew: CourseRegisterDTO = {
      employee_id: this.employeeId,
      course_name: this.course_name,
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
    this.courseService.addCourseRegister(addNew);
            
  }

}


