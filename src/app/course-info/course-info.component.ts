import { EmployeeService } from './../Service/employee.service';
import { Component, Inject, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import {MatDialog , MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { courseUpdate } from '../model/courseUpdate';
import { Employee } from '../model/Employee';

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {

  searchText: any;

  constructor(
    private courseService : CourseService,

    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getCourseInformation();
  }

  cInfo : any;
  getCourseInformation(){
    this.courseService.getCourseInfo().subscribe(
      (ci) => this.cInfo = ci
    )
  }

  onEdit( c : courseUpdate) {
    this.dialog.open(DialogUpdateComponent, {
      width: '35%',
      data : c
    }).afterClosed().subscribe(val => {
      this.getCourseInformation();
    });
  }
  deleteC(eid: number) {
    this.courseService.deleteCourse(eid).subscribe({
      next:(res)=>{
        alert("Xoá thành công !!!");
      },
      error: () =>{
        alert("Xoá thất bại !!!");
      }
    })
  }



}

@Component({
  selector: 'dialog-update',
  templateUrl: './dialog-update.html',
  styleUrls: ['./dialog-update.css']
})
export class DialogUpdateComponent implements OnInit {
  courseForm!: FormGroup;
  public employees: any;
  public courses: any;

  constructor(
    private formBuilder : FormBuilder,
    private dialog : MatDialog,
    private courseService : CourseService,
    private employeeService: EmployeeService,
    @Inject(MAT_DIALOG_DATA) public editData : any,
   ) { }

  ngOnInit(): void {
    this.getCourseInformation();
    this.GetEmployees();
    this.GetCourse();



    this.courseForm = this.formBuilder.group({
      employee : [''],
      course : [''],
      certLink : [''],
      status : [''],
      platform : [''],
      category : [''],
      startDate : [''],
      endDate : [''],
      courseLength : ['']
    });

    if(this.editData)
    {
      this.courseForm.controls['employee'].setValue(this.editData.employeeId);
      this.courseForm.controls['course'].setValue(this.editData.courseId);
      this.courseForm.controls['certLink'].setValue(this.editData.certLink);
      this.courseForm.controls['status'].setValue(this.editData.status);
      this.courseForm.controls['platform'].setValue(this.editData.platform);
      this.courseForm.controls['category'].setValue(this.editData.category);
      this.courseForm.controls['startDate'].setValue(this.editData.startDate);
      this.courseForm.controls['endDate'].setValue(this.editData.endDate);
      this.courseForm.controls['courseLength'].setValue(this.editData.courseLength);
    }

    console.log('dc', this.editData);
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

  cInfo : any;
  getCourseInformation(){
    this.courseService.getCourseInfo().subscribe(
      (ci) => this.cInfo = ci
    )
  }

  update(){
    console.log('courseForm', this.courseForm.value);
    console.log('editData', this.editData);

    var start = new Date(this.courseForm.controls['startDate'].value).toISOString().slice(0,10);
    // start = start + " 00:00:00";
    var end = new Date(this.courseForm.controls['endDate'].value).toISOString().slice(0,10);
    // end = end + " 00:00:00";
    console.log('start', start);

    //editData: data cu
    //courseform.value: data moi

    var newItem: courseUpdate = {
      newId:{
        course_id: this.courseForm.controls['course'].value,
        employee_id: this.courseForm.controls['employee'].value,
      },
      oldId: {
        course_id: this.editData.courseId,
        employee_id: this.editData.employeeId
      },
        cert_link: this.courseForm.controls['certLink'].value,
        status: this.courseForm.controls['status'].value*1,
        start_date: start,
        end_date: end,
        is_deleted: 0
    }
    console.log('newItem', newItem);



    this.courseService.UpdateCourse(newItem).subscribe(
      data=>{
      console.log('send', data);

    })
  }


//   next: (res) => {
//     alert("Cập nhật thành công !!!");
//     this.courseForm.reset();
//     this.dialog.closeAll();
//   },
//   error:()=>{
//     alert("Cập nhật thất bại !!!");
//   }
// })

}
