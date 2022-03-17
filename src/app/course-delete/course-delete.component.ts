import { SelectionModel } from '@angular/cdk/collections';
import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CourseService } from '../course.service';
import { CourseDelete, CourseDelete2, CourseDeleteDto } from '../model/course-delete';

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
    private courseService : CourseService
  ) { }

  ngOnInit(): void {
    this.getCourseInformation();
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
    this.courseService.getCourseInfo().subscribe(
      (ci) => {
        console.log("ci", ci);
        this.cInfo = ci}
    )
  }

  deleteC() {
    console.log('id-selected', this.deleteItem);
    var deleteItemDto: CourseDelete = {
      course_id: this.deleteItem.courseId,
      employee_id: this.deleteItem.employeeId
    }
    console.log('item-convert', deleteItemDto);
    this.courseService.deleteCourse(deleteItemDto).subscribe((data)=>{
      console.log('item-send', data);
    })
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
      this.courseService.deleteMultipleCourse(listItemArr).subscribe((data)=>{
        console.log('send-data', data);
      });
      $('#notification2').modal('show');





			// this.productService.deleteProducts(selectedProducts) // Angular 9
        //                 this.productService.deleteProducts(selectedProducts as number[]) // Angular 13
				// .subscribe(res => {
				// 	this.clss = 'grn';
				// 	this.msg = 'Products successfully deleted';
				// 	}, err => {
        //                 this.clss = 'rd';
				// 		this.msg = 'Something went wrong during deleting products';
        //             }
        //         );
		} else {


			this.clss = 'rd';
			this.msg = 'You must select at least one product';
		}
  }





}
