import { Component, Inject, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Course } from '../course';
import { EmployeeService } from '../Service/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {


  p: number = 1; //paging

  public employees!: any[];

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

  // GetCourseCategory(){
  //   console.log('course', this.courses);
  //   this.courses.forEach(element => {
  //     this.filterCategory.push(element.category);
  //   });
  //   console.log('ok');
  //   console.log('filter', this.filterCategory);
  // }

  // showCategory(category: string) {
  //   const dkAll = this.filterCategory === 'All';
  //   const dkCate = this.filterCategory === 'this.courses.selector.component.categories';
  //   return dkAll || dkCate;
  // }

  // showPlatform(platform: string) {
  //   const dkAll = this.filterCategory === 'All';
  //   const dkPlat = this.filterPlatform === 'this.platform';
  //   return dkAll || dkPlat;
  // }

  courseDetail(c:any){
    this.data = c;
  }

  showListStudent(data: any){
    console.log('courseClick', data);

    console.log('emps', this.employees);
    this.employees.forEach(element => {
      if(element.courseId==data.id){
        this.listStudent.push(element);
      }
    });
    console.log('listStu', this.listStudent);
  }

  rowClick(employee: any){
    const app = document.getElementsByClassName("btn-close")[1] as HTMLElement;
    app?.click();
    console.log('row clicked', employee);
    this.router.navigate(['user-screen', employee.employeeId]);
  }


}