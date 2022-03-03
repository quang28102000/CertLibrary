import { CourseServiceService } from './../course-service.service';
import { CourseRegister } from './../course-register';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  CourseRegister: CourseRegister[];
  constructor( private CourseServiceService: CourseServiceService) { }

  ngOnInit(): void {
  }

  public onAddCourse(addForm: NgForm): void {
    document.getElementById('add-employee-form').click();
    this.CourseServiceService.addCourseRegister(addForm.value).subscribe(
      (response: CourseRegister) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
}
