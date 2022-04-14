import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { CourseEmployeeService } from '../Service/course-employee.service';
import { CourseService } from '../Service/course.service';
import { EmployeeService } from '../Service/employee.service';
import { UserScreenService } from '../Service/user-screen.service';

@Component({
  selector: 'app-user-screen',
  templateUrl: './user-screen.component.html',
  styleUrls: ['./user-screen.component.css']
})

export class UserScreenComponent implements OnInit {

  public id!:any;
  public info:any;
  router: any;
  searchText : any;
  public eId = this.id;

  public employees: any;
  public statictics: number[]= [];


  constructor(
    private userScreenService: UserScreenService,
    private courseEmployeeService: CourseEmployeeService,
    private courseService: CourseService,
    private route: ActivatedRoute,
    private dialogRef: MatDialog,
    private employeeService: EmployeeService
  ) { }

  ngOnInit(): void {
    window.scrollTo(0,0);
    this.id = this.route.snapshot.paramMap.get('id');
    this.GetInfomation(this.id);
  }

  GetInfomation(userId: string){
    this.employeeService.getInfomation(userId).subscribe((data) =>{
        console.log(data);
        this.info = data;
        console.log("user",this.info);
      },
      (error: HttpErrorResponse) =>{
        console.log("error",error.message);
      }
    );

    this.courseEmployeeService.getCourseEmployees().subscribe((data)=>{
      data = data.filter(emp => emp.employeeId == userId);
      console.log("employee-course info: ", data);
    })
    
    this.courseEmployeeService.getCourseEmployees().subscribe(res => {
      // status: đã đk, chưa đk, đã hoàn thành, chưa hoàn thành

      var totalCourse =  res.filter(item => item.employeeId==this.route.snapshot.paramMap.get('id'));
      //Da dang ky
      var registerCourse =  totalCourse.filter(item => item.status == 1);
      var notRegister =  totalCourse.filter(item => item.status == 2);
      var completeCourse =  totalCourse.filter(item => item.status == 3);
      var incompleteCourse =  totalCourse.filter(item => item.status == 4);


      
      this.employees = res;

      this.statictics[0] = totalCourse.length;
      this.statictics[1] = registerCourse.length;
      this.statictics[2] = notRegister.length;
      this.statictics[3] = completeCourse.length;
      this.statictics[4] = incompleteCourse.length;
    });
  }

  cert:any;
  skills:any;
  getCert(): void {
    this.courseEmployeeService.getCertificate().subscribe(
      (c) => this.cert = c
    )
    this.getSkill()
  }



  getSkill(){
    this.courseService.getSkills().subscribe(
      (s) => this.skills = s

    );console.log("s", this.skills);
  }



  onOpen(i : string) {
    this.dialogRef.open(DialogCourseRecent, {
      data: {
        id : this.id,
        cn : i

      }
    });

  }

}

@Component({
  selector: 'dialog-course-recent',
  templateUrl: './dialog-course-recent.html',
  styleUrls: ['./dialog-course-recent.css']
})
export class DialogCourseRecent implements OnInit{

  public CRid: any;
  public cName : any;


  constructor(private userScreenService: UserScreenService,
    private courseEmployeeService: CourseEmployeeService,
    private courseService:CourseService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.CRid = data.id
      this.cName = data.cn
  }

  courses: any;
  getCourseRecent(): void {
    this.courseEmployeeService.getCourseEmployees().subscribe(
      (updateCourseR) => this.courses = updateCourseR
    )
  }

  skills : any;
  getSkill(){
    this.courseService.getSkills().subscribe(
      (s) => this.skills = s

    );console.log("s", this.skills)
  }

  ngOnInit(): void {
    this.getCourseRecent();
    this.getSkill();
  }

}