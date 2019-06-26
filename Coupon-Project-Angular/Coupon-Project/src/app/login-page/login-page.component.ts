import { Component, OnInit} from '@angular/core';
import { LoggedUser } from '../entities/logged-user';
import { LogginService } from '../Servises/loggin.service';
import {  Router } from '@angular/router';
import { ClientType } from '../entities/ClientType';
import { CheckConService } from '../Servises/check-con.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {


  public loggedUser:LoggedUser =new LoggedUser(0,null,null,null);
  public loging:boolean = false;
  public erorr:string[] = [];


  constructor(public logginServise:LogginService, private router:Router,private check:CheckConService) { }

  ngOnInit() {

    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });
  }
    
   public loggin(){
      

      this.logginServise.login(this.loggedUser).subscribe(c =>{
        this.loggedUser = c;
        this.logginServise.setLoggedUser = this.loggedUser;
        switch (c.clientType) {
          case ClientType.ADMIN:
            this.logginServise.ifLoggdIn(this.loggedUser);
            this.router.navigate(["adminPersonalArea"]);
            break;
            case ClientType.COMPANY:
            this.logginServise.ifLoggdIn(this.loggedUser);
            this.router.navigate(["comapnyPersonalArea"]);
            break;
            case ClientType.CUSTOMER:
            this.logginServise.ifLoggdIn(this.loggedUser);
            this.router.navigate(["home"]);
            break;
         
        }
      },err => {
       
        if( err.error.messages.find( (x: string) => x.includes("Something wrong happened... Please contact the admin.null")  ))  {
          this.erorr.push("Please choose a type")
          alert( this.erorr)
          
        }else{
          alert(err.error.messages)
        } 


       
        

        this.erorr.splice(0,this.erorr.length)

      });
     
      }
      
    
  };



  



