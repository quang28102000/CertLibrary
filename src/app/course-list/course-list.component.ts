import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { CourseService } from '../course.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Course } from '../course';
import { EmployeeService } from '../Service/employee.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  displayedColumns: string[] = ['index', 'fullName', 'email','course', 'status', 'startDate', 'endDate'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  p: number = 1; //paging

  public employees!: any[];
  public platformList: any;

  filterCategory: String[] = [];
  filterPlatform: String[] = [];
  courses!:Course[];
  data:any;
  searchTxt: string = '';
  optionCategory: string = '';
  optionPlatform: string = '';

  listStudent: any[] = [];



  course_category: any;
  constructor(private course: CourseService,
    private employeeService: EmployeeService,
    private router: Router,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.GetAllInfo();
  }


  GetAllInfo(){
    this.course.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list b3", res);

      res.forEach(element => {
        console.log('e', element.category);
        this.filterCategory.push(element.category);
        this.filterPlatform.push(element.platform);
        
      });

      this.filterCategory = this.filterCategory.filter((v, i, a) => a.indexOf(v) === i);
      this.filterPlatform = this.filterPlatform.filter((v, i, a) => a.indexOf(v) === i);

      console.log('filter category', this.filterCategory);
      console.log('filter platform', this.filterPlatform);
    });

    this.employeeService.getAll().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })


  }

  courseDetail(c:any){
    this.data = c;
  }

  showListStudent(data: any){
    console.log('courseClick', data);
    console.log('emps', this.employees);
    this.listStudent = [];
    this.employees.forEach(element => {
      if(element.courseId==data.id){
        this.listStudent.push(element);
      }
    });
    console.log('listStu', this.listStudent);
    //set stt
    for (let index = 0; index < this.listStudent.length; index++) {
      this.listStudent[index].index = index+1;        
    }
    this.dataSource = new MatTableDataSource(this.listStudent);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  rowClick(employee: any){
    const app = document.getElementsByClassName("btn-close")[1] as HTMLElement;
    app?.click();
    console.log('row clicked', employee);
    this.router.navigate(['user-screen', employee.employeeId]);
  }

  filterCountry(event:any) {
    this.platformList = this.filterPlatform;
    //in a real application, make a request to a remote url with the query and return filtered results, for demo we filter at client side
    let filtered : any[] = [];
    let query = event.query;

    for(let i = 0; i < this.filterPlatform.length; i++) {
        let country = this.filterPlatform[i];
        if (country.toLowerCase().indexOf(query.toLowerCase()) == 0) {
            filtered.push(country);
        }
    }

    this.platformList = filtered;
  }


}