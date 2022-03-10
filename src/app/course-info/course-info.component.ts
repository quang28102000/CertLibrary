import { Component, Inject, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import {MatDialog , MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';

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
    this.courseService.getCourseInfo().subscribe((ci) => {
      this.cInfo = ci;
      console.log("course", ci);
    }
    )
  }

  onEdit( c : any) {
    this.dialog.open(DialogUpdateComponent, {
      width: '35%',
      data : c
    }).afterClosed().subscribe(val => {
      this.getCourseInformation();
    });
  }
  deleteC(id: number) {
    this.courseService.deleteCourse(id).subscribe({
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

  constructor(
    private formBuilder : FormBuilder,
    private dialog : MatDialog,
    private courseService : CourseService,
    @Inject(MAT_DIALOG_DATA) public editData :any,
   ) { }

  ngOnInit(): void {
    this.courseForm = this.formBuilder.group({
      fullName : [''],
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
      this.courseForm.controls['fullName'].setValue(this.editData.fullName);
      this.courseForm.controls['course'].setValue(this.editData.course);
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

  update(){
    this.courseService.UpdateCourse(this.courseForm.value,this.editData.id).subscribe({
      next: (res) => {
        alert("Cập nhật thành công !!!");
        this.courseForm.reset();
        this.dialog.closeAll();
      },
      error:()=>{
        alert("Cập nhật thất bại !!!");
      }
    })
  }






}
