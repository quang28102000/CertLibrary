import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component } from '@angular/core';
import { Course } from './course';
import { CourseService } from './course.service';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { UserProfile } from './user-profile';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  courses!: Course[];
  userProfile!: UserProfile;
  employees!: Employee[];

  constructor(private courseService: CourseService,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployees();
    this.getUserProfile();
    this.getCourses(); 
  }

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

  public getUserProfile(): void {
    this.employeeService.getUserProfile().subscribe(
      (response: UserProfile) => {
        this.userProfile = response;
        console.log(response);
      }, (error: HttpErrorResponse) => alert(error.message)
    );
  };

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
