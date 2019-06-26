import { Component, OnInit } from '@angular/core';
import { LoggedUser } from 'src/app/entities/logged-user';
import { LogginService } from 'src/app/Servises/loggin.service';


@Component({
  selector: 'app-heder',
  templateUrl: './heder.component.html',
  styleUrls: ['./heder.component.css']
})
export class HederComponent implements OnInit   {
 
 
  public loginBtn:string = "login";
  public name:string = "Geust";
  public logOutBtn:string = "Log Out";

  public login:boolean = false ;
  
  public loggedUser:LoggedUser = new LoggedUser(0,null,null,null);



  constructor(public loggedUserServise:LogginService) { }

  ngOnInit() {

    
  if( this.loggedUserServise.getLoggduser().id != 0 ){
    this.login = true;
  } 
    }
    
    
    public logginOut(){
        this.loggedUserServise.logOut().subscribe(() =>{
         console.log("secsess") 
          this.loggedUserServise.ifLoggdOut(this.loggedUser);

        },err =>{
          this.loggedUserServise.setLoggedUser = this.loggedUser;
          alert(err.error.messages)
        })
    }

   
    

  }




