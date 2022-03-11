import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { EmployeeService } from '../Service/employee.service';
import { CourseRegisterDTO, CourseRegisterDTO2 } from '../model/course-register';
import { map, Observable, startWith } from 'rxjs';
import { FormControl } from '@angular/forms';
import { Employee } from '../model/Employee';


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
  public employee_name: any;
  public employee_info!: string;
  public platform!: any;
  public status!: any;
  public start!: any;
  public totalTime!: any;
  public course_info!: string;
  public end!: any;
  public certLink!: any;

  public categorySelected!: any;
  public courseCategory: String[] = [];
  public coursePlatform: String[] = [];

  public courseSelected: any;


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
      res.forEach(element => {
        this.filterCategory.push(element.category);
        this.filterPlatform.push(element.platform);
      });
      this.filterCategory = this.filterCategory.filter((v, i, a) => a.indexOf(v) === i);
      this.filterPlatform = this.filterPlatform.filter((v, i, a) => a.indexOf(v) === i);

      console.log('filter category', this.filterCategory);
      console.log('filter platform', this.filterPlatform);

    })

    // this.courseCategory = this.courseService.getCourseCategory();
    // this.coursePlatform = this.courseService.coursePlatform;

    // console.log(this.courseCategory);
  }

  selectEmployee(item: any){
    console.log('id',item);

    const app = document.getElementsByClassName("btn-close")[0] as HTMLElement;
    app?.click();

    this.emp = item;
  }




  Add(){
    var startDate = new Date(this.start).toISOString().slice(0, 10);
    var endDate = new Date(this.end).toISOString().slice(0, 10);
    

    startDate = startDate + " 00:00:00"; 
    endDate = endDate + " 00:00:00"; 

    console.log("startDate", startDate);
    // var course_name = this.course_info.split(' ` ')[1];
    // var course_id = Number(this.course_info.split(' ` ')[0]);

    // if(Number.isNaN(course_id)){
    //   console.log('courseid',course_id)
    //   course_id = this.courses.pop().id + 1;
    // }

    const addNew2: CourseRegisterDTO2 = {
      course:{
        course_tittle: this.courseSelected.name,
        platform: this.platform,
        category: this.categorySelected,
        // totalLength: this.totalTime
      },
      employee:{
        full_name: this.emp.fullName,
        email: this.emp.email,
      },
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

    console.log('add',addNew2);
    this.courseService.addCourseRegister(addNew2).subscribe((data)=>{
      console.log("send-data: ", data);
    })
            
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


