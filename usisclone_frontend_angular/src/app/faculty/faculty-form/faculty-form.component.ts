import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup ,Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { FacultyService } from 'src/app/services/facultyService';
import { ToastrService } from 'ngx-toastr';
import { Faculty } from 'src/app/models/models';


@Component({
  selector: 'app-faculty-form',
  templateUrl: './faculty-form.component.html',
  styleUrls: ['./faculty-form.component.css']
})
export class FacultyFormComponent implements OnInit {

  facultyInformationForm! : FormGroup;

  constructor(
    private fb: FormBuilder,
    private facultyService : FacultyService,
    private router : Router,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
    this.facultyInformationForm = this.fb.group({
      faculty_name: [null, Validators.required],
      faculty_email: [null, Validators.required],
      faculty_mobileNumber: [null, Validators.required],
    })
  }

  submitInformationOfFaculty():void{
    console.log(this.facultyInformationForm);
    const formData:any = new FormData();
    Object.keys(this.facultyInformationForm.controls).forEach(key => {
      console.log(this.facultyInformationForm.get(key)?.value);
      formData.append(
        key, this.facultyInformationForm.get(key)?.value
      );
    });
  }

}
