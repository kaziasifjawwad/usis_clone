import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacultyFormComponent } from './faculty-form/faculty-form.component';
import { FacultyRoutingModule } from './faculty-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AllFacultiesComponent } from './all-faculties/all-faculties.component';



@NgModule({
  declarations: [
    FacultyFormComponent,
    AllFacultiesComponent
  ],
  imports: [
    CommonModule,
    FacultyRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FacultyModule { }
