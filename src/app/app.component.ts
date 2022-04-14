import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Course } from './course';
import { CourseService } from './Service/course.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public courses!: Course[];

  constructor(private courseService: CourseService){}


  // ngOnInit() {
  //   this.getCourses();
  // }

  // public getCourses(): void{
  //   this.courseService.getCourses().subscribe(
  //     (response: Course[]) =>{
  //       this.courses = response;
  //       console.log(response);
  //     },
  //     (error: HttpErrorResponse) =>{
  //       alert(error.message);
  //     }
  //   )
  // }
}
