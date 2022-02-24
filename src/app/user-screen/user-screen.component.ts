import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserScreenService } from '../Service/user-screen.service';

@Component({
  selector: 'app-user-screen',
  templateUrl: './user-screen.component.html',
  styleUrls: ['./user-screen.component.css']
})
export class UserScreenComponent implements OnInit {

  public id!:any; 
  public info:any;

  constructor(
    private userScreenService: UserScreenService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.GetInfomation(this.id);
  }

  GetInfomation(userId: string){
    this.userScreenService.getInfomation(userId).subscribe((data) =>{
        console.log(data);
        this.info = data;
        console.log("hh",this.info)
        console.log("oo", this.id[0]?.email)
      },
      (error: HttpErrorResponse) =>{
        console.log("error",error.message);
      }
    )
  }

}
