import { Component, OnInit } from '@angular/core';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-course-delete',
  templateUrl: './course-delete.component.html',
  styleUrls: ['./course-delete.component.css']
})
export class CourseDeleteComponent implements OnInit {

  searchText: any;
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

  deleteC(id: number) {
    console.log('id-delete', id);
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
