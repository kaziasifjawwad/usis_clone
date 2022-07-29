import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseModule } from './course/course.module';
import { FacultyModule } from './faculty/faculty.module';




@NgModule({
  declarations: [
    AppComponent,
    
  ],


  imports: [
    BrowserModule,
    CourseModule,
    FacultyModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(
      {
        timeOut : 5000,
        positionClass : 'toast-top-right',
      }
    )
  ],


  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
