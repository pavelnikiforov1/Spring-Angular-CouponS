import { Component, OnInit } from '@angular/core';
import { LoggedUser } from 'src/app/entities/logged-user';
import { Coupon } from 'src/app/entities/Coupon';
import { UtilityService } from 'src/app/Servises/utility.service';
import { LogginService } from 'src/app/Servises/loggin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  public company:LoggedUser = new LoggedUser(0,null,null,null)
  public couponList:Coupon[];
  public loggeduser:LoggedUser = new LoggedUser(0,null,null,null)



  constructor(private loginService:LogginService,private router:Router) { }
  ngOnInit() { 

    this.company = this.loginService.getLoggduser() ;
    console.log(this.company)  

    if(this.company.id == 0){
      alert("you are not logged in !");
        this.router.navigate(['login']);
    }
  }

}
