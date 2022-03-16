import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EmployeeService } from '../Service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  displayColumns: string[] = ['index', 'fullName', 'email','course', 'status', 'startDate', 'endDate'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    
  }

  public totalLength: any; // paging
  p: number = 1; //paging

  constructor(private employeeService: EmployeeService,
    private router: Router) {
  }

  public employees!: any[];
  ngOnInit(): void {
    this.GetEmployeeList();
    this.totalLength = this.employees.length;
  }

  GetEmployeeList(){
    this.employeeService.getAll().subscribe(res => {
      //set stt
      for (let index = 0; index < res.length; index++) {
        res[index].index = index+1;        
      }
      this.employees = res;
      this.dataSource = new MatTableDataSource(res);
      console.log(res);
    })

  }

  rowClick(employee: any){
    console.log('row clicked', employee);
    this.router.navigate(['user-screen', employee.employeeId]);
  }

}
