import { Component, OnInit } from '@angular/core';
import { LoggedUser } from 'src/app/entities/logged-user';
import { Coupon } from 'src/app/entities/Coupon';
import { LogginService } from 'src/app/Servises/loggin.service';
import { Router } from '@angular/router';
import { CheckConService } from 'src/app/Servises/check-con.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public admin:LoggedUser = new LoggedUser(0,null,null,null)
  public couponList:Coupon[];
  public loggeduser:LoggedUser = new LoggedUser(0,null,null,null)



  constructor(private loginService:LogginService,private router:Router,private check:CheckConService) { }

  ngOnInit() {
   
    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });


    this.admin = this.loginService.getLoggduser() ;
    console.log(this.admin)  

    if(this.admin.id == 0){
      alert("you are not logged in !");
        this.router.navigate(['login']);
    }
 

  }
}