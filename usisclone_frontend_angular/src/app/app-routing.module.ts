import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { FacultyModule } from "./faculty/faculty.module";
import { FacultyFormComponent } from "./faculty/faculty-form/faculty-form.component";


const routes:Routes = [

    {
        path: '', component: FacultyFormComponent,
    },

    {
        path: 'faculty',
        loadChildren: () =>
        FacultyModule
           
    }
]

@NgModule({

    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})


export class AppRoutingModule { }
export const routingComponents = [FacultyFormComponent]