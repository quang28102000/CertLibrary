import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../Service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  public totalLength: any; // paging
  p: number = 1; //paging

  constructor(private employeeService: EmployeeService,
    private router: Router) { }

  public employees!: any[];
  ngOnInit(): void {
    this.GetEmployeeList();
    this.totalLength = this.employees.length;

  }

  GetEmployeeList(){
    this.employeeService.getAll().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })

  }

  rowClick(employee: any){
    console.log('row clicked', employee);
    this.router.navigate(['user-screen', employee.employeeId]);
  }

}
