import { SelectionModel } from '@angular/cdk/collections';
import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CourseService } from '../Service/course.service';
import { CourseDelete, CourseDelete2, CourseDeleteDto } from '../model/course-delete';
import { CourseEmployeeService } from '../Service/course-employee.service';

@Component({
  selector: 'app-course-delete',
  templateUrl: './course-delete.component.html',
  styleUrls: ['./course-delete.component.css']
})
export class CourseDeleteComponent implements OnInit {
  p: number = 1; //paging

  deleteItems: any[] =[];

  checkAllValue: any;

  dataSource!: MatTableDataSource < any > ;  
  selection = new SelectionModel < any > (true, []);  
  searchText: any;
  deleteItem: any;

  msg: string = ''; // Angular 13
  clss: string = ''; // Angular 13

  cInfo : CourseDeleteDto[] = [];
  constructor(
    private courseEmployeeService: CourseEmployeeService
  ) { }

  ngOnInit(): void {
    this.getCourseInformation();
  }

  checkAnItem(event: any, course: CourseDeleteDto){
    console.log('event',event.target?.checked);
    console.log('course',course);
    this.cInfo.forEach(element => {
      if(element==course){
        console.log('e', element);
        element.checked=event.target?.checked;
      }
    });
  }


  checkAll(){
    console.log(this.checkAllValue);
    if(this.checkAllValue == true){
      this.cInfo.forEach(element => {
        element.checked = true;
      });
    }else if(this.checkAllValue == false){
      this.cInfo.forEach(element => {
        element.checked = false;
      });
    }
  }

  getCourseInformation(){
    this.courseEmployeeService.getCourseEmployees().subscribe(
      (ci) => {
        console.log("ci", ci);
        this.cInfo = ci}
    ) 
  }

  deleteC() {
    console.log('id-selected', this.deleteItem);
    //đổi item thành object CourseDelete
    var deleteItemDto: CourseDelete = {
      course_id: this.deleteItem.courseId,
      employee_id: this.deleteItem.employeeId
    }
    console.log('item-convert', deleteItemDto);
    //gọi Service để xoá
    this.courseEmployeeService.deleteCourse(deleteItemDto).subscribe((data)=>{
      console.log('item-send', data);
    })
    //filter loại item ra khỏi list sau khi đã xoá
    this.cInfo = this.cInfo.filter(item => item != this.deleteItem);
    //hiện thông báo
    $('#notification2').modal('show');
  }

  ClickBtnDelete(item: any){
    this.deleteItem = item;
  }

  MultipleDelete(){
    //lấy ra danh sách item đã chọn
    const selectedItems = this.cInfo.filter(cInfo => cInfo.checked);
		console.log ('selectedItem',selectedItems);

    //tạo object
    var listItemArr: CourseDelete2 = {
      course_id: [],
      employee_id: []
    };
		
		if(selectedItems && selectedItems.length > 0) {
      // convert list item đã chọn sang object courseDelete: employeeId[], courseId[]
      selectedItems.forEach(element => {
        listItemArr.course_id.push(element.courseId);
        listItemArr.employee_id.push(element.employeeId);
      });
      console.log('list I', listItemArr);
      //gọi service để xoá
      this.courseEmployeeService.deleteMultipleCourse(listItemArr).subscribe((data)=>{
        console.log('send-data', data);
      });
      //realtime delete, xoá item ra khỏi list
      selectedItems.forEach(element => {
        this.cInfo = this.cInfo.filter(item => item != element);
      });
      $('#notification2').modal('show');
		} else {
			this.clss = 'rd';
			this.msg = 'You must select at least one product';
		}
  }
}
