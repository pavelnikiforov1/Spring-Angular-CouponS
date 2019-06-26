import { Component, OnInit } from '@angular/core';
import { LogginService } from '../Servises/loggin.service';
import { Router } from '@angular/router';
import { LoggedUser } from '../entities/logged-user';
import { CheckConService } from '../Servises/check-con.service';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public loggeduser:LoggedUser = new LoggedUser(0,null,null,null);
  public loggdIn:boolean = false;


  constructor(private logginServise:LogginService , private router:Router ,private check:CheckConService) { }

  ngOnInit() {

    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });

  }

  public goToPersonalErea(){
    this.loggeduser = this.logginServise.getLoggduser();
    console.log(this.loggeduser)

    if(this.loggeduser.id != 0){
    
    if(this.loggeduser.clientType === "CUSTOMER"){
      this.router.navigate(["customerPersonalArea"])
    }

    if(this.loggeduser.clientType === "COMPANY"){
      this.router.navigate(["comapnyPersonalArea"])
    }
   
  }else{
    this.router.navigate(["login"])
  }
  }

  
}
