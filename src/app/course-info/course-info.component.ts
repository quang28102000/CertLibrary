import { EmployeeService } from './../Service/employee.service';
import { Component, Inject, OnInit } from '@angular/core';
import { CourseService } from '../Service/course.service';
import {MatDialog , MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { courseUpdate } from '../model/courseUpdate';
import { Employee } from '../model/Employee';
import { CourseDelete } from '../model/course-delete';
import { CourseEmployeeService } from '../Service/course-employee.service';

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {

  p: number = 1; //paging
  searchText: any;
  deleteItem: any;

  constructor(
    private courseEmployeeService : CourseEmployeeService,

    public dialog: MatDialog) { }

  ngOnInit(): void {
    window.scrollTo(0,0);
    this.getCourseInformation();
  }

  cInfo : any;
  getCourseInformation(){
    this.courseEmployeeService.getCourseEmployees().subscribe(
      (ci) => this.cInfo = ci
    );
    console.log('Infomation', this.cInfo);
  }

  onEdit( c : courseUpdate) {
    this.dialog.open(DialogUpdateComponent, {
      width: '35%',
      data : c
    }).afterClosed().subscribe(val => {
      this.getCourseInformation();
    });
  }

  ClickBtnDelete(item: any){
    this.deleteItem = item;
  }

  deleteC() {
    console.log('id-selected', this.deleteItem);
    var deleteItemDto: CourseDelete = {
      course_id: this.deleteItem.courseId,
      employee_id: this.deleteItem.employeeId
    }
    console.log('item-convert', deleteItemDto);
    this.courseEmployeeService.deleteCourse(deleteItemDto).subscribe((data)=>{
      console.log('item-send', data);
    })
    $('#notification2').modal('show');
  }


  pageChange(){
    window.scrollTo(0,500);
  }

}




// ############################

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
    private courseEmployeeService: CourseEmployeeService,
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
    this.courseEmployeeService.getCourseEmployees().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })
  }

  GetCourse(){
    this.courseService.getAllCourse().subscribe(res => {
      this.courses = res;
      console.log("course-list", res);

    })
  }

  cInfo : any;
  getCourseInformation(){
    this.courseEmployeeService.getCourseEmployees().subscribe(
      (ci) => this.cInfo = ci
    )
  }

  update(){
    console.log('courseForm', this.courseForm.value);
    console.log('editData', this.editData);

    var start = new Date(this.courseForm.controls['startDate'].value).toISOString().slice(0,10);
    var end = new Date(this.courseForm.controls['endDate'].value).toISOString().slice(0,10);
    console.log('start', start);

    var newItem: courseUpdate = {
      newId:{
        course_id: this.courseForm.controls['course'].value,
        employee_id: this.courseForm.controls['employee'].value,
      },
      oldId: {
        course_id: this.editData.courseId,
        employee_id: this.editData.employeeId
      },
        certLink: this.courseForm.controls['certLink'].value,
        status: this.courseForm.controls['status'].value*1,
        start_date: start,
        end_date: end,
        isDeleted: 0
    }
    console.log('newItem', newItem);

    this.courseEmployeeService.update(newItem).subscribe(
      data =>{
        console.log("Update Success");
        $("#success_alert").modal("toggle");
        this.dialog.closeAll();
      },
      error => {
        console.log("Update Fail");
        $("#fail_alert").modal("toggle");
        this.dialog.closeAll();
      }
    )
  }
}
