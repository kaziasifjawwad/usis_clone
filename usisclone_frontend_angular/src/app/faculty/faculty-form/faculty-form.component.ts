import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup ,Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { FacultyService } from 'src/app/services/facultyService';
import { ToastrService } from 'ngx-toastr';
import { Faculty } from 'src/app/models/models';
import { HttpEvent } from '@angular/common/http';


@Component({
  selector: 'app-faculty-form',
  templateUrl: './faculty-form.component.html',
  styleUrls: ['./faculty-form.component.css']
})
export class FacultyFormComponent implements OnInit {

  facultyInformationForm! : FormGroup;
  imageFile!:File;
  imageInputField!:Event|null;


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

  uploadFile(event:Event){
     this.imageFile = <File>(event.target as HTMLInputElement).files![0] ;
   
  }

  submitInformationOfFaculty():void{
    const formData:any = new FormData();

    for(const key of Object.keys(this.facultyInformationForm.controls)){
      // console.log(this.facultyInformationForm.controls[key].value);
      this.facultyInformationForm.controls[key].markAsDirty();
      this.facultyInformationForm.controls[key].updateValueAndValidity();
    }

    formData.append('facultyInformation',JSON.stringify(this.facultyInformationForm.value));
    formData.append('image',this.imageFile);

    console.log(formData.get('image'));
    console.log(formData.get('facultyInformation'));
    this.facultyService.saveFacultyProfile(formData)
    .subscribe(
      {
      next:(res)=>{
        console.log(res);
        this.toaster.success('Profile created');
        // this.router.navigate(['/login'])
      },
      error:(error)=>{
        this.facultyInformationForm.reset();
        this.toaster.error('Please give valid information');
        // (this.imageInputField.target as HTMLInputElement).files[0] = null;
      }
      
    });
  }

}
