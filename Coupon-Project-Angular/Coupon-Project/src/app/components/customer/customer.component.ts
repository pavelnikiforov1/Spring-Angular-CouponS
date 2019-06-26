import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/entities/Customer';
import { Coupon } from 'src/app/entities/Coupon';
import { CustomerService } from 'src/app/Servises/customer.service';
import { UtilityService } from 'src/app/Servises/utility.service';
import { LoggedUser } from 'src/app/entities/logged-user';
import { LogginService } from 'src/app/Servises/loggin.service';
import { CheckConService } from 'src/app/Servises/check-con.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {



  constructor(private check:CheckConService , private router:Router) { }

  ngOnInit() {
    
    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });
  }

}
