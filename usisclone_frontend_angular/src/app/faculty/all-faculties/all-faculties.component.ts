import { Component, OnInit } from '@angular/core';
import { FacultyService } from 'src/app/services/facultyService';
import { HttpErrorResponse } from '@angular/common/http';
import { Faculty } from 'src/app/models/models';


@Component({
  selector: 'app-all-faculties',
  templateUrl: './all-faculties.component.html',
  styleUrls: ['./all-faculties.component.css']
})
export class AllFacultiesComponent implements OnInit {

  public faculties: Faculty[] = [];

  constructor(private facultyService:FacultyService) { }

  ngOnInit(): void {
    this.getAllFacultyInfo();
  }



  getAllFacultyInfo(){
    this.facultyService.readAllFaculties().subscribe(
      (Response:Faculty[])=>{
        this.faculties = Response;
        console.log(this.faculties);
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

}
