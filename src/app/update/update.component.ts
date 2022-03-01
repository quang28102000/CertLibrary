import { Information } from './../information';


import { Component, Input, OnInit } from '@angular/core';
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  constructor() { }

  // @Input() Infor;
  ngOnInit(): void {
  }
  Infor: Information[] = [{
    full_name: 'Nguyễn Văn A' ,
    course_id: 3 ,
    employee_id: 7 ,
    status: 1 ,
    start_date: new Date ('2022-02-01') ,
    end_date: new Date ('') ,
    cert_link: '' ,
    course_tittle: 'The Complete SQL Bootcamp 2022: Go from Zero to Hero' ,
    category: 'SQL' ,
    platform: 'Udemy' ,
    course_length: '8.51' ,
  }, {
    full_name: 'Nguyễn Việt Á' ,
    course_id: 6 ,
    employee_id:10 ,
    status: 2 ,
    start_date: new Date ('2021-12-23 00:00:00') ,
    end_date: new Date ('2022-01-17 00:00:00') ,
    cert_link: 'https://coursera.org/share/e068ba156767852577c088171de5c304' ,
    course_tittle: 'Connecting Front-End to Back-End' ,
    category: 'Fullstack' ,
    platform: 'CodeCademy' ,
    course_length: '33' ,
  }]

  removeInfo(id: number): boolean {
    const index = this.findIndexById(id);
    if (index !== -1) {
      this.Infor.splice(index, 1);
      return true;
    }

    return false;
  }

  findIndexById(id: number): number {
    return this.Infor.findIndex(inf => inf.course_id === id);
  }
}
