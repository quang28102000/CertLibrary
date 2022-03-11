import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { CourseDelete } from '../model/course-delete';

@Component({
  selector: 'app-course-delete',
  templateUrl: './course-delete.component.html',
  styleUrls: ['./course-delete.component.css']
})
export class CourseDeleteComponent implements OnInit {

  searchText: any;
  deleteItem: any;
  constructor(
    private courseService : CourseService
  ) { }

  ngOnInit(): void {
    this.getCourseInformation();
  }

  cInfo : any;
  getCourseInformation(){
    this.courseService.getCourseInfo().subscribe(
      (ci) => {
        console.log("ci", ci);
        this.cInfo = ci}
    )
  }

  deleteC() {
    console.log('id-delete', this.deleteItem);
    var deleteItemDto: CourseDelete = {
      courseId: this.deleteItem.courseId,
      employeeId: this.deleteItem.employeeId
    }
    this.courseService.deleteCourse(deleteItemDto).subscribe({
      next:(res)=>{
        alert("Xoá thành công !!!");
      },
      error: () =>{
        alert("Xoá thất bại !!!");
      }
    })
  }

  ClickBtnDelete(item: any){
    console.log('clicked', item);
    this.deleteItem = item;

  }



}
