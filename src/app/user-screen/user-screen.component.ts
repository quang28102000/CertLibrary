import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute,
    private dialogRef: MatDialog,
    private employeeService: EmployeeService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.GetInfomation(this.id);
  }

  GetInfomation(userId: string){
    this.userScreenService.getInfomation(userId).subscribe((data) =>{
        console.log(data);
        this.info = data;
        console.log("user",this.info);
      },
      (error: HttpErrorResponse) =>{
        console.log("error",error.message);
      }
    );
    
    this.employeeService.getAll().subscribe(res => {
      // status: 1.Đang học, 2. Đã học, 3. Chưa thi, 4. Thi nhưng chưa có bằng 

      var totalCourse =  res.filter(item => item.employeeId==this.route.snapshot.paramMap.get('id'));
      //Da dang ky
      var studyCourse =  totalCourse.filter(item => item.status == 1);
      var completeCourse =  totalCourse.filter(item => item.status == 2);

      
      this.employees = res;
      console.log("list1", totalCourse);
      console.log("list2", studyCourse);

      this.statictics[0] = totalCourse.length;
      this.statictics[1] = studyCourse.length;
      this.statictics[2] = completeCourse.length;
    });
  }

  cert:any;
  skills:any;
  getCert(): void {
    this.userScreenService.getCertificate().subscribe(
      (c) => this.cert = c
    )
    this.getSkill()
  }



  getSkill(){
    this.userScreenService.getSkills().subscribe(
      (s) => this.skills = s

    );console.log("s", this.skills)
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
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.CRid = data.id
      this.cName = data.cn
  }

  courses: any;
  getCourseRecent(): void {
    this.userScreenService.getCourseR().subscribe(
      (updateCourseR) => this.courses = updateCourseR
    )
  }

  skills : any;
  getSkill(){
    this.userScreenService.getSkills().subscribe(
      (s) => this.skills = s

    );console.log("s", this.skills)
  }

  ngOnInit(): void {
    this.getCourseRecent();
    this.getSkill();
  }

}