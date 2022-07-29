import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacultyFormComponent } from './faculty-form/faculty-form.component';
import { FacultyRoutingModule } from './faculty-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FacultyFormComponent
  ],
  imports: [
    CommonModule,
    FacultyRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FacultyModule { }
