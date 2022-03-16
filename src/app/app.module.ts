import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import {MatDialogModule} from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { UserScreenComponent } from './user-screen/user-screen.component';
import { MatTableModule } from '@angular/material/table'; 
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import { CourseListComponent } from './course-list/course-list.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { FormsModule } from '@angular/forms';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { CourseRegisterComponent } from './course-register/course-register.component';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import {ReactiveFormsModule} from '@angular/forms';
import { CourseInfoComponent } from './course-info/course-info.component';

import { DialogCourseRecent } from './user-screen/user-screen.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { DialogUpdateComponent } from './course-info/course-info.component';
import {MatRadioModule} from '@angular/material/radio';
import { CourseDeleteComponent } from './course-delete/course-delete.component';
import { ManagerComponent } from './manager/manager.component';
import {MatCheckboxModule} from '@angular/material/checkbox';

import {MatIconModule} from '@angular/material/icon'
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserScreenComponent,
    CourseListComponent,
    EmployeeListComponent,
    CourseRegisterComponent,
    CourseInfoComponent,
    DialogCourseRecent,
    DialogUpdateComponent,
    CourseDeleteComponent,
    ManagerComponent
    
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
    Ng2SearchPipeModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    MatRadioModule,
    MatCheckboxModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
