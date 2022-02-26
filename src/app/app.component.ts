import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Course } from './course';
import { CourseDto } from './course-dto';
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

  // statistics
  statistics!: Map<Number, Employee[]>

  // courses dto
  coursesDto!: CourseDto[];

  constructor(private courseService: CourseService,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getUserProfile();

    this.getEmployees();
    this.getUserHomePageStatistics();

    // this.getCourses();
    this.getCoursesDto();
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
  
  public getCoursesDto(): void {
    this.courseService.getCoursesDto().subscribe(
      (response: CourseDto[]) => {
        this.coursesDto = response;
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

  public getUserHomePageStatistics(): void {
    // Map<status, Map<count, employees>>
    const employeesSubcribedCourse = new Map<Number, Map<Number, Employee[]>>();

    this.employeeService.getEmployees().subscribe(
      (employees: Employee[]) => {
        // đã đăng ký
        employeesSubcribedCourse.set(
          1,
          new Map().set(
            employees.filter(emp => Number(emp.status) === 1).length,
            employees.filter(emp => Number(emp.status) === 1)
          )
        );

        // chưa đăng ký
        employeesSubcribedCourse.set(
          2,
          new Map().set(
            employees.filter(emp => Number(emp.status) === 2).length,
            employees.filter(emp => Number(emp.status) === 2)
          )
        );
        
        // đã hoàn thành
        employeesSubcribedCourse.set(
          3,
          new Map().set(
            employees.filter(emp => Number(emp.status) === 3).length,
            employees.filter(emp => Number(emp.status) === 3)
          )
        );

        // chưa hoàn thành
        employeesSubcribedCourse.set(
          4,
          new Map().set(
            employees.filter(emp => Number(emp.status) === 4).length,
            employees.filter(emp => Number(emp.status) === 4)
          )
        );

        console.log(...[...employeesSubcribedCourse.entries()]);

      }
    )
  }

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
