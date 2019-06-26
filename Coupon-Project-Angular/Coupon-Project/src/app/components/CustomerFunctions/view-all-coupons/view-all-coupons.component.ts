import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CompanyService } from 'src/app/Servises/company.service';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/Servises/customer.service';

@Component({
  selector: 'app-view-all-coupons',
  templateUrl: './view-all-coupons.component.html',
  styleUrls: ['./view-all-coupons.component.css']
})
export class ViewAllCouponsComponent implements OnInit {

  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);

  public customerCoupons:Coupon[]; 
  public erorr:string[] = [];

  constructor(private customerServise:CustomerService ,private router:Router) { }

  ngOnInit() {

    this.customerServise.viewAllCustomerCoupons().subscribe(c=>{  
      console.log(c)
      this.customerCoupons = c;
    }, err => {
       if(err.error.status == 401){
    alert("you are not logged in !");
    this.router.navigate(['login']);
  }
      if( err.error.messages.find( (x: string) => x.includes("opps somthing went wrong  no purchased coupons for this customer ")  ))  {
        this.erorr.push(" No purchased coupons for this customer")
      } 

      alert(this.erorr)
    })

  }




}
