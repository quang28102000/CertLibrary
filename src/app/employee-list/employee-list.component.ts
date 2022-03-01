import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../Service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  constructor(private employeeService: EmployeeService) { }

  public employees!: any[];
  ngOnInit(): void {
    this.GetEmployeeList();

  }

  GetEmployeeList(){
    this.employeeService.getAll().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })

  }

}
