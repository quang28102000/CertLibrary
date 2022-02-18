import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Course } from './course';
import { CourseService } from './course.service';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  courses!: Course[];
  numberOfCourses!: Number;

  incompleteCoursesByEmployee!: Number;
  registeredCoursesByEmployee!: Number;
  unregisteredCourseByEmployee!: Number;
  completeCourseByEmployee!: Number;
  numberOfEmployeesInLast7days!: Number;

  constructor(private courseService: CourseService,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getCourses();
    this.getNumberOfCourses();
    // 
    this.getIncompleCoursesByEmployee();
    this.getRegisteredCoursesByEmployee();
    this.getUnregisteredCoursesByEmployee();
    this.getCompleteCoursesByEmployee();
    this.getNumberOfEmployeesInLast7days();
  }

  public getIncompleCoursesByEmployee(): void {
    this.employeeService.getIncompleteCoursesByEmployee().subscribe(
      (response: Number) => {
        this.incompleteCoursesByEmployee = response;
        console.log(response);
      }, (error: HttpErrorResponse) => console.log(error.message)
    );
  };

  public getRegisteredCoursesByEmployee(): void {
    this.employeeService.getRegisteredCoursesByEmployee().subscribe(
      (response: Number) => {
        this.registeredCoursesByEmployee = response;
        console.log(response);
      }, (error: HttpErrorResponse) => console.log(error.message)
    );
  }

  public getUnregisteredCoursesByEmployee(): void {
    this.employeeService.getUnregisteredCoursesByEmployee().subscribe(
      (response: Number) => {
        this.unregisteredCourseByEmployee = response;
        console.log(response);
      }, (error: HttpErrorResponse) => console.log(error.message)
    );
  }

  public getCompleteCoursesByEmployee(): void {
    this.employeeService.getCompleteCoursesByEmployee().subscribe(
      (response: Number) => {
        this.completeCourseByEmployee = response;
        console.log(response);
      }, (error: HttpErrorResponse) => console.log(error.message)
    );
  };
 
  public getNumberOfEmployeesInLast7days(): void {
    this.employeeService.getNumberOfEmployeesInLast7days().subscribe(
      (response: Number) => {
        this.numberOfEmployeesInLast7days = response;
        console.log(response);
      }, (error: HttpErrorResponse) => console.log(error.message)
    );
  };

  public getCourses(): void {
    this.courseService.getCourses().subscribe(
      (response: Course[]) => {
        this.courses = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getNumberOfCourses(): void {
    this.courseService.getNumberOfCourses().subscribe(
      (response: Number) => {
        this.numberOfCourses = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
