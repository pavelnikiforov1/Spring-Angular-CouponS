import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/entities/Customer';
import { Coupon } from 'src/app/entities/Coupon';
import { LoggedUser } from 'src/app/entities/logged-user';
import { UtilityService } from 'src/app/Servises/utility.service';
import { LogginService } from 'src/app/Servises/loggin.service';
import { Router } from '@angular/router';
import { CheckConService } from 'src/app/Servises/check-con.service';

@Component({
  selector: 'app-customer-main',
  templateUrl: './customer-main.component.html',
  styleUrls: ['./customer-main.component.css']
})
export class CustomerMainComponent implements OnInit {

  public customer:LoggedUser = new LoggedUser(0,null,null,null)
  public couponList:Coupon[];
  public loggeduser:LoggedUser = new LoggedUser(0,null,null,null)



  constructor(private loginService:LogginService,private router:Router,private check:CheckConService) { }

  ngOnInit() {
    
    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });

    this.customer = this.loginService.getLoggduser() ;
    console.log(this.customer)  

    if(this.customer.id == 0){
      alert("you are not logged in !");
        this.router.navigate(['login']);
    }
 

  }

}
