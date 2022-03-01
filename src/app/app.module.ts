import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import {MatDialogModule} from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { DialogEmployee } from './home/home.component';
import { UserScreenComponent } from './user-screen/user-screen.component';
import { MatTableModule } from '@angular/material/table'; 
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import { CourseListComponent } from './course-list/course-list.component';
import { DialogCourse } from './course-list/course-list.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { FormsModule } from '@angular/forms';
import { EmployeeListComponent } from './employee-list/employee-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserScreenComponent,
    DialogEmployee,
    DialogCourse,
    CourseListComponent,
    EmployeeListComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule,
    CommonModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
