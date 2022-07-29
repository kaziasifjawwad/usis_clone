
import { Injectable } from "@angular/core";
import { HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { Faculty } from "../models/models";
import { config } from "../config";

@Injectable({
    providedIn:'root'
})

export class FacultyService{
    constructor(private http: HttpClient){}

    readResult():Observable<Faculty[]>{
        console.log(`${config.apiUrl}/api/faculty`);
        return this.http.get<Faculty[]>(
            `${config.apiUrl}/api/faculty`
        )
    }

    // requestResult(metaData:{endpoint:string,userfile:FormData}):Observable<any[]>{
    //     const anyRequest =  this.http.post<any[]>(`${config.apiUrl}/userfiles/upload/${metaData.endpoint}` , metaData.userfile);
    //     return anyRequest;
    // }




    // downloadAsTxtFile(metaData:{resultId:string,type :string}){
    //     console.log("i hit again");
    //     return this.http.get(`${config.apiUrl}/userfiles/${metaData.type}/${metaData.resultId}` , { responseType: 'blob' });
    // }

    // deleteById(fileId:string){
    //     return this.http.get(`${config.apiUrl}/userfiles/delete/${fileId}`);
    // }

    // requestResultForcImageNormal(data :{file:File, mail: string},apiEndPoint:string){
    //     return this.http.post<{message:string,itemsRemaining:number,status:number}>(`${config.apiUrl}/userfiles/${apiEndPoint}/` ,data);
    // }


}