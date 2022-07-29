import { RouterModule, Routes } from '@angular/router';
import { NgModule } from "@angular/core";
import { FacultyFormComponent } from './faculty-form/faculty-form.component';

const routes : Routes = [
    {path:'create-faculty-profile',component:FacultyFormComponent}
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports : [RouterModule]
})

export class FacultyRoutingModule{}