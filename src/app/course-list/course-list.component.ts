import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { CourseService } from '../course.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Course } from '../course';
import { EmployeeService } from '../Service/employee.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { courseCreate } from '../model/courseC';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  displayedColumns: string[] = ['index', 'fullName', 'email','course', 'status', 'startDate', 'endDate'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  p: number = 1; //paging

  public employees!: any[];
  public platformList: any;

  filterCategory: String[] = [];
  filterPlatform: String[] = [];
  courses!:Course[];
  data:any;
  searchTxt: string = '';
  optionCategory: string = '';
  optionPlatform: string = '';

  listStudent: any[] = [];



  course_category: any;
  constructor(private course: CourseService,
    private employeeService: EmployeeService,
    private router: Router,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.GetAllInfo();
  }


  GetAllInfo(){
    this.course.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list b3", res);

      res.forEach(element => {
        console.log('e', element.category);
        this.filterCategory.push(element.category);
        this.filterPlatform.push(element.platform);
        
      });

      this.filterCategory = this.filterCategory.filter((v, i, a) => a.indexOf(v) === i);
      this.filterPlatform = this.filterPlatform.filter((v, i, a) => a.indexOf(v) === i);

      console.log('filter category', this.filterCategory);
      console.log('filter platform', this.filterPlatform);
    });

    this.employeeService.getAll().subscribe(res => {
      this.employees = res;
      console.log("employee-list", res);
    })


  }

  courseDetail(c:any){
    this.data = c;
  }

  showListStudent(data: any){
    console.log('courseClick', data);
    console.log('emps', this.employees);
    this.listStudent = [];
    this.employees.forEach(element => {
      if(element.courseId==data.id){
        this.listStudent.push(element);
      }
    });
    console.log('listStu', this.listStudent);
    //set stt
    for (let index = 0; index < this.listStudent.length; index++) {
      this.listStudent[index].index = index+1;        
    }
    this.dataSource = new MatTableDataSource(this.listStudent);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  rowClick(employee: any){
    const app = document.getElementsByClassName("btn-close")[1] as HTMLElement;
    app?.click();
    console.log('row clicked', employee);
    this.router.navigate(['user-screen', employee.employeeId]);
  }

  filterCountry(event:any) {
    this.platformList = this.filterPlatform;
    //in a real application, make a request to a remote url with the query and return filtered results, for demo we filter at client side
    let filtered : any[] = [];
    let query = event.query;

    for(let i = 0; i < this.filterPlatform.length; i++) {
        let country = this.filterPlatform[i];
        if (country.toLowerCase().indexOf(query.toLowerCase()) == 0) {
            filtered.push(country);
        }
    }

    this.platformList = filtered;
  }
  createC() {
    const dialogRef = this.dialog.open(CourseCreateComponent, {
      width: '53%'
    }
      );
  }


}



@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.html',
  styleUrls: ['./course-create.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ]
})


export class CourseCreateComponent implements OnInit {

  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  skilldb!: any[];
  
  public courses: any;
  sk =
    {
      skill_name:  [] as any,
      skill_id: [] as any    
    };
    @ViewChildren('checkbox') private myCheckboxes! : QueryList<any>;


  constructor(
    private _formBuilder: FormBuilder,
    public courseService: CourseService
    ) {
  }




  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      tittle: ['', Validators.required],
      platform: ['', Validators.required],
      category: ['', Validators.required],
      image: ['', Validators.required],
      courseLength: ['', Validators.required],

    });
    this.secondFormGroup = this._formBuilder.group({
      newName: [''],
    });
    this.getS();

    this.courseService.getList().subscribe(res => {
      this.courses = res;
      console.log("course-list b3", res);
    });
  }


  getS(){
    this.courseService.getSkills().subscribe(
      (s) => this.skilldb = s
    )
  }

  checkSkills(s: any, event: MatCheckboxChange){
    if(event.checked == true){
      this.sk.skill_id.push(s.id);
      this.sk.skill_name.push(s.name);
    }
    else{
      this.sk.skill_id = this.sk.skill_id.filter((obj: any) => obj !== s.id); 
      this.sk.skill_name = this.sk.skill_name.filter((obj: any) => obj !== s.name);
    }
    console.log('s', this.sk);
  }


  onCreate(){
    var flat = this.sk.skill_id.length;
    this.newSkills.skill_id.forEach((element: number) => {
        this.sk.skill_id.push(element);
    });
    this.newSkills.skill_name.forEach((element: any) => {
      this.sk.skill_name.push(element);
    });

    console.log('flat', flat);
    console.log('skills', this.sk);




    const addC: courseCreate = {
          course_id: Math.max(...this.courses.map((o: { id: any; }) => o.id), 0)+1,
          tittle: this.firstFormGroup.controls['tittle'].value,
          platform: this.firstFormGroup.controls['platform'].value,
          category: this.firstFormGroup.controls['category'].value,
          image: this.firstFormGroup.controls['image'].value,
          courseLength: this.firstFormGroup.controls['courseLength'].value,
          skills: this.sk,
          skill_flag: flat,
    };
    console.log('add',addC);

    this.courseService.courseCreate(addC).subscribe(data=>
      {
        console.log("send-data: ", data);
      }
    )
    var alert = document.getElementById('alert-success') as HTMLElement;
    alert.setAttribute("style", "display: block");
  }

  public newSkills = {
    skill_name: [] as any,
    skill_id: [] as any
  };
  public skillIdCount = 1;
  addSkill() {
    this.newSkills.skill_id.push(Math.max(...this.skilldb.map(o => o.id), 0)+this.skillIdCount);
    this.skillIdCount++;
    this.newSkills.skill_name.push(this.secondFormGroup.controls['newName'].value);

    console.log("newskill", this.newSkills.skill_id, this.newSkills.skill_name);

    this.secondFormGroup.controls['newName'].setValue('');
  }
  deleteSkill(id: number){
    for(let i=0; i< this.sk.skill_id.length; i++){
      if(i == id){
        var id_value = this.sk.skill_id.splice(i, 1);
        this.sk.skill_name.splice(i, 1);
        
        let myCheckboxes = this.myCheckboxes.toArray();
        myCheckboxes[this.skilldb.findIndex(x => x.id == id_value)].checked = false;
      }
    }
    //const index = this.sk.skill_id.findIndex(si => si === id);

  }

  deleteSkill2(id: number){
    for(let i=0; i< this.newSkills.skill_id.length; i++){
      if(i == id){
        this.newSkills.skill_id.splice(i, 1);
        this.newSkills.skill_name.splice(i, 1);
      }
    }
    //const index = this.sk.skill_id.findIndex(si => si === id);

  }

}