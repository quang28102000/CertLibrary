import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { UserScreenService } from '../Service/user-screen.service';

@Component({
  selector: 'app-user-screen',
  templateUrl: './user-screen.component.html',
  styleUrls: ['./user-screen.component.css']
})

export class UserScreenComponent implements OnInit {

  public id!:any;
  public info:any;
  router: any;


  constructor(
    private userScreenService: UserScreenService,
    private route: ActivatedRoute,
    private dialogRef: MatDialog
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



  onOpen(Id: number) {
    this.dialogRef.open(DialogCourseRecent, {
      data: {
        id : Id
      }
    });

  }

}

@Component({
  selector: 'dialog-course-recent',
  templateUrl: './dialog-course-recent.html',
  styleUrls: ['./dialog-course-recent.css']
})
export class DialogCourseRecent implements OnInit{

  public CRid: any;


  constructor(private userScreenService: UserScreenService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.CRid = data.id
  }

  courses: any;
  getCourseRecent(cId: number): void {
    this.userScreenService.getCourseR(cId).subscribe(
      (updateCourseR) => this.courses = updateCourseR
    )
  }

  ngOnInit(): void {
    this.getCourseRecent(this.CRid);
  }

}

