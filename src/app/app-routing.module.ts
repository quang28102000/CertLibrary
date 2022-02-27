import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseListComponent } from './course-list/course-list.component';
import { HomeComponent } from './home/home.component';
import { UserScreenComponent } from './user-screen/user-screen.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', component: HomeComponent},
  {path: 'user-screen/:id', component: UserScreenComponent},
  {path: 'course-list', component: CourseListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  
  exports: [RouterModule]
})
export class AppRoutingModule { }
