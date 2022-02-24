import { DataSource } from '@angular/cdk/table';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { CourseService } from '../course.service';
import { EmployeeService } from '../employee.service';
import { of } from 'rxjs';
import { RouteConfigLoadEnd, Router } from '@angular/router';

export interface DialogData {
  data: any[];
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public registeredCoursesByEmployee!: any;
  public unregisteredCoursesByEmployee!: any;
  public numberOfCourses!: any;
  public numberOfEmployeesInLast7days!: any;
  public completeCourseByEmployee!: any;
  public incompleteCourseByEmployee!:any;

  public employees!: any[];
  

  constructor(public dialog: MatDialog,
    private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.GetNumber();
  }

  Click(){
    this.dialog.open(DialogEmployee, {
      data: this.employees
    });
    if(this.employeeService.isDialogClosed== true){
      this.dialog.closeAll();
    } 
  }

  Click2(){
    // this.dialog.open(DialogCourse, {
    //   data: this.employees
    // });
  }

  GetNumber(){
    this.employeeService.getRegisteredCoursesByEmployee().subscribe(
      (response: any) =>{
        this.registeredCoursesByEmployee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getUnregisteredCoursesByEmployee().subscribe(
      (response: any) =>{
        this.unregisteredCoursesByEmployee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getNumberOfCourses().subscribe(
      (response: any) =>{
        this.numberOfCourses = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getNumberOfEmployeesInLast7days().subscribe(
      (response: any) =>{
        this.numberOfEmployeesInLast7days = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getCompleteCourseByEmployee().subscribe(
      (response: any) =>{
        this.completeCourseByEmployee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getIncompleteCourseByEmployee().subscribe(
      (response: any) =>{
        this.incompleteCourseByEmployee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )

    this.employeeService.getIncompleteCourseByEmployee().subscribe(
      (response: any) =>{
        this.incompleteCourseByEmployee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )
  }
    
}


@Component({
  selector: 'dialog-employee',
  templateUrl: 'dialogEmployee.html',
  styleUrls: ['dialogEmployee.css']
})
export class DialogEmployee {
  public employees!: any[];
  constructor(@Inject(MAT_DIALOG_DATA) public data: any[],
    private courseService: CourseService,
    private router: Router,
    public dialog: MatDialog) {
      
  }

  displayedColumns = ['id', 'name', 'email', 'status', 'course_title', 'category'];

  dataSource = new ExampleDataSource();

isExpansionDetailRow = (i: number, row: Object) => row.hasOwnProperty('detailRow');
  expandedElement: any;
  test() {
    console.log('test');
  }

  cellClicked(element: { id: string; }) {
    console.log(element.id + ' cell clicked');
    this.courseService.isDialogClosed == true;
    this.router.navigate(['user-screen', element.id]);
  }
}

export interface Element {
  id: string,
  name: string,
  email:string,
  status:number,
  course_title:string,
  category: string
}

const ELEMENT_DATA: Element[] = [
  {id:'ANV1', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV2', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV3', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV4', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV5', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV6', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV7', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
  {id:'ANV8', name:'Nguyen Van A', email:'ANV1@fsoft.com.vn', course_title:'Basic Angular 4x Programming', status:1, category:'Angular'},
];

export class ExampleDataSource extends DataSource<any> {
  connect(): Observable<Element[]> {
    const rows: Element[] = [];
    ELEMENT_DATA.forEach(element => rows.push(element));
    console.log(rows);
    return of(rows);
  }

  disconnect() { }
}


// @Component({
//   selector: 'dialog-course',
//   templateUrl: 'dialogCourse.html',
//   styleUrls: ['dialogCourse.css']
// })
// export class DialogCourse {
//   public employees!: any[];
//   constructor(@Inject(MAT_DIALOG_DATA) public data: any[],
//     private employeeService: EmployeeService) {
      
//   }

//   GetAllEmployee(){
//     this.employeeService.getCourses().subscribe(
//       (response: any[]) =>{
//         this.employees = response;
//         console.log(response);
//       },
//       (error: HttpErrorResponse) =>{
//         alert(error.message);
//       }
//     )
//   }
// }
