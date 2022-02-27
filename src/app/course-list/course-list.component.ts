import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  filterCategory = 'All';
  filterPlatform = 'All';
  courses:any;

  course_category: any;
  constructor(private course: CourseService) {
  }

  ngOnInit(): void {
    this.course.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list b3", res);
    })
  }

  showCategory(category: string) {
    const dkAll = this.filterCategory === 'All';
    const dkCate = this.filterCategory === 'this.courses.selector.component.categories';
    return dkAll || dkCate;
  }

  showPlatform(platform: string) {
    const dkAll = this.filterCategory === 'All';
    const dkPlat = this.filterPlatform === 'this.platform';
    return dkAll || dkPlat;
  }


}
