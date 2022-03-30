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

  searchFilter: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChildren('checkbox') private myCheckboxes! : QueryList<any>;

  p: number = 1; //paging

  public employees!: any[];
  public platformList: any;

  filterCategory: String[] = [];
  filterPlatform: String[] = [];
  public courses!:Course[];
  data:any;
  searchTxt: string = '';
  optionCategory: string = '';
  optionPlatform: string = '';

  listStudent: any[] = [];

  public disableInputText = true;
  public display_chooseSkill= false;
  public skilldb: any;
  public selectSkills ={
    skill_id: [] as any,
    skill_name: [] as any
  }
  
  public updateCourse = {
    course_name: '',
    skills: [],
    platform: '',
    category: '',
    totalLength: ''
  }

  public display = true;



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
    });

    this.course.getSkills().subscribe(
      (s) => this.skilldb = s
    );


  }

  courseDetail(c:any){
    //reset update course
    this.updateCourse = {
      course_name: '',
      skills: [],
      platform: '',
      category: '',
      totalLength: ''
    }

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

  deleteCourse(id: number){
    this.course.courseDelete(id).subscribe({
      next:(res)=>{
        alert("Xoá thành công !!!");
      },
      error: () =>{
        alert("Xoá thất bại !!!");
      }
    })
  }

  clickEditBtn(){
    // // ẩn hiện input text và btn add
    // this.disableInputText = !this.disableInputText;
    // this.display_chooseSkill = !this.display_chooseSkill;
    // // ẩn btn chỉnh sửa, hiện btn lưu
    // var editBtn = document.getElementById('edit') as HTMLElement;
    // editBtn.setAttribute("style", "display: none");
    // var saveBtn = document.getElementById('save') as HTMLElement;
    // saveBtn.setAttribute("style", "display: block");

    // //ẩn tên, hiện ô input tên
    // var name_data = document.getElementById('name_data') as HTMLElement;
    // name_data.setAttribute("style", "display: none");
    // var name_update = document.getElementById('name_update') as HTMLElement;
    // name_update.setAttribute("style", "display: block");



    this.changeStyle(true);
  }

  changeStyle(value: boolean){
    var str = value ? 'none':'block';
    var str2 = value ? 'block':'none';

    // ẩn hiện input text và btn add
    this.disableInputText = !this.disableInputText;
    this.display_chooseSkill = !this.display_chooseSkill;
    // ẩn btn chỉnh sửa, hiện btn lưu
    var editBtn = document.getElementById('edit') as HTMLElement;
    editBtn.setAttribute("style", "display: " + str);
    var saveBtn = document.getElementById('save') as HTMLElement;
    saveBtn.setAttribute("style", "display: "+ str2);

    //ẩn tên, hiện ô input tên
    var name_data = document.getElementById('name_data') as HTMLElement;
    name_data.setAttribute("style", "display: " + str);
    var name_update = document.getElementById('name_update') as HTMLElement;
    name_update.setAttribute("style", "display: " + str2);

  }

  ChooseSkills(skillsCourse: []){
    //reset các checkbox
    let myCheckboxes = this.myCheckboxes.toArray();
    myCheckboxes.forEach(element => {
      element.checked = false;
    });

    //check các skills cũ của course
    for (let index = 0; index < skillsCourse.length; index++) {
      const element = skillsCourse[index];
      myCheckboxes[this.skilldb.findIndex((x: { name: string; }) => x.name == element)].checked = true;
    }

    //check các skills mới của course (nếu đã chọn)
    for (let index = 0; index < this.selectSkills.skill_id.length; index++) {
      const element = this.selectSkills.skill_id[index];
      myCheckboxes[this.skilldb.findIndex((x: { id: string; }) => x.id == element)].checked = true;
    }

    $('#popup3').modal('hide');
    $('#chooseSkills').modal({backdrop: 'static', keyboard: false});
    $('#chooseSkills').modal('toggle');
    $("#chooseSkills").attr('aria-hidden', 'true');
    

  }

  submitSkills(){
    //reset select skills
    this.selectSkills ={
      skill_id : [],
      skill_name : []
    } 

    //ẩn modal coursedetail hiện modal chọn skill
    $('#popup3').modal('toggle');
    $('#chooseSkills').modal('hide');
    
    //lấy ra list các skills (chỉ có id) đã được check
    let myCheckboxes = this.myCheckboxes.toArray();
    var checkedSkills = myCheckboxes.filter(checkbox => checkbox.checked == true).map(a => a.value);
    
    //lấy ra list các skill (id, name) đã được check
    this.skilldb.forEach((element: { id: string; name: string }) => {
      if(checkedSkills.find(a => a == element.id)){
        this.selectSkills.skill_id.push(element.id);
        this.selectSkills.skill_name.push(element.name);
      }
    });

    console.log('checked', this.selectSkills);

    //ẩn các skill cũ của khoá
    var skill_data = document.getElementsByClassName('skill_data');
    for (let index = 0; index < skill_data.length; index++) {
      const element = skill_data[index];
      element.setAttribute("style", "display: none");
    }

    //hiển thị skills đã được chỉnh sửa
    var update_skill = document.getElementsByClassName('update_skill');
    for (let index = 0; index < update_skill.length; index++) {
      const element = update_skill[index];
      element.setAttribute("style", "display: block");
    }
  }

  SaveCourse(){
    this.updateCourse.skills = this.selectSkills.skill_id;
    console.log('coursePlatform', this.updateCourse);
    this.changeStyle(false);

    //gọi service
    this.course.update(this.updateCourse).subscribe(data=>
      {
        console.log("send-data: ", data);
        var alert = document.getElementById('alert-success') as HTMLElement;
        alert.setAttribute("style", "display: block");
      },
      error=>{
        console.log("error-add", error);
        var alert = document.getElementById('alert-fail') as HTMLElement;
        alert.setAttribute("style", "display: block");
      }
    )
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

    //check nếu thiếu thông tin thì báo lỗi và return
    for(const field in this.firstFormGroup.controls){
      var control = this.firstFormGroup.controls[field];
      if(control.value == null){
        var alert = document.getElementById('alert-fail') as HTMLElement;
        alert.setAttribute("style", "display: block");
        return;
      };
    }

    //gọi service
    this.courseService.courseCreate(addC).subscribe(data=>
      {
        console.log("send-data: ", data);
        var alert = document.getElementById('alert-success') as HTMLElement;
        alert.setAttribute("style", "display: block");
      },
      error=>{
        console.log("error-add", error);
        var alert = document.getElementById('alert-fail') as HTMLElement;
        alert.setAttribute("style", "display: block");
      }
    )
    


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

  resetForm(){
    let myCheckboxes = this.myCheckboxes.toArray();
    myCheckboxes.forEach(element => {
      element.checked = false;
    });
  }

}