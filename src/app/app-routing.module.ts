import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseAddComponent } from './course-add/course-add.component';
import { CourseDeleteComponent } from './course-delete/course-delete.component';
import { CourseInfoComponent } from './course-info/course-info.component';
import { CourseListComponent } from './course-list/course-list.component';
import { CourseRegisterComponent } from './course-register/course-register.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { HomeComponent } from './home/home.component';
import { ManagerComponent } from './manager/manager.component';
import { UserScreenComponent } from './user-screen/user-screen.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', component: HomeComponent},
  {path: 'user-screen/:id', component: UserScreenComponent},
  {path: 'course-list', component: CourseListComponent},
  {path: 'employee-list', component: EmployeeListComponent},
  {path: 'course-register', component: CourseRegisterComponent},
  {path: 'course-info', component: CourseInfoComponent},
  {path: 'course-delete', component: CourseDeleteComponent},
  {path: 'manager', component: ManagerComponent},
  {path: 'course-add', component: CourseAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  
  exports: [RouterModule]
})
export class AppRoutingModule { }
