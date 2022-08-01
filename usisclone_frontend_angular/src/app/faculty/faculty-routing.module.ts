import { RouterModule, Routes } from '@angular/router';
import { NgModule } from "@angular/core";
import { FacultyFormComponent } from './faculty-form/faculty-form.component';
import { AllFacultiesComponent } from './all-faculties/all-faculties.component';

const routes : Routes = [
    {path:'create-faculty-profile',component:FacultyFormComponent},
    {path:'all-faculty-profile',component:AllFacultiesComponent}
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports : [RouterModule]
})

export class FacultyRoutingModule{}