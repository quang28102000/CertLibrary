import { InformationCopy } from './../model/information-copy';
import { InformationService } from 'src/app/Service/information.service';

import { Course } from './../model/course';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { CourseService } from '../course.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Information } from '../model/information';

@Component({
  selector: 'app-search-auto-complete',
  templateUrl: './search-auto-complete.component.html',
  styleUrls: ['./search-auto-complete.component.css'],
})
export class SearchAutoCompleteComponent implements OnInit {
  // title!: any[];
  // myControl = new FormControl();
  // courses!: CourseDto[];
  // options!: CourseDto[];
  // filteredOptions: Observable<String | CourseDto[]> | undefined;

  // constructor(private courseService: CourseService) {}
  // ngOnInit() {
  //   this.getTitle();
  //   this.filteredOptions = this.myControl.valueChanges.pipe(
  //     startWith(''),
  //     map((value) => (typeof value === 'string' ? value : value.name)),
  //     map((name) => (name ? this._filter(name) : this.courses[0].name))
  //   );
  // }

  // displayFn(Course: CourseDto[]): string {
  //   return Course && Course[0].name ? Course[0].name : '';
  // }

  // private _filter(name: string): CourseDto[] {
  //   const filterValue = name.toLowerCase();

  //   return this.options.filter((option) =>
  //     option.name.toLowerCase().includes(filterValue)
  //   );
  // }

  // public getTitle(): void {
  //   this.courseService.getList().subscribe(
  //     (res) => {
  //       this.courses = res;
  //       console.log('list: ', res);
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

  myControl = new FormControl();
  options!: Information[];
  filteredOptions!: Observable<Information[]>;
  constructor(private InformationService: InformationService) {}
  ngOnInit() {
    this.getInformationDto();
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map((value) => (typeof value === 'string' ? value : value.name)),
      map((name) => (name ? this._filter(name) : this.options))
    );
  }

  displayFn(user: Information): String {
    return user && user.course_Tittle ? user.course_Tittle : '';
  }

  private _filter(name: string): Information[] {
    const filterValue = name.toLowerCase();

    return this.options.filter((option) =>
      option.course_Tittle.toLowerCase().includes(filterValue)
    );
  }

  public getInformationDto(): void {
    this.InformationService.getInformation().subscribe(
      (res) => {
        this.options = res;
        console.log('list: ', this.options);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
