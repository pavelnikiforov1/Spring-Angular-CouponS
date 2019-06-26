import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckConService {

  constructor(public httpClinet:HttpClient) { }



  public checkCon():Observable<String>{
    return this.httpClinet.get<String>("http://localhost:8080/main/checkCon",{withCredentials:true});
  }
}
