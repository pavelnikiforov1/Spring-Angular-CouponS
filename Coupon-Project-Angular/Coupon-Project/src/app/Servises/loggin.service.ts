import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedUser } from 'src/app/entities/logged-user';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class LogginService {

  private  loggdUser:LoggedUser = new LoggedUser(0,null,null,null);
  



public constructor(private httpClient : HttpClient) { }

public login(loggedUser:LoggedUser):Observable<LoggedUser>{

  return this.httpClient.post<LoggedUser>("http://localhost:8080/login/login",loggedUser,{withCredentials:true});
}

public logOut():Observable<any>{

  return this.httpClient.post<any>("http://localhost:8080/login/logOut",{withCredentials:true});
}


  public ifLoggdIn(l:LoggedUser){
    
    this.loggdUser = l;
  }

  public ifLoggdOut(l:LoggedUser){
    this.loggdUser = l;
  }

  public getLoggduser(){
    return this.loggdUser;
  }


  public get loggeduser() : LoggedUser{
    return this.loggdUser;
  }

  public set setLoggedUser(loggedUser:LoggedUser){
    this.loggdUser = loggedUser;
  }
}
