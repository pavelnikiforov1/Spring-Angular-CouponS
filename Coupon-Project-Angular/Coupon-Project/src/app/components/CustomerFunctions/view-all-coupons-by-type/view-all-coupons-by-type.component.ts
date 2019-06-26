import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/Servises/customer.service';

@Component({
  selector: 'app-view-all-coupons-by-type',
  templateUrl: './view-all-coupons-by-type.component.html',
  styleUrls: ['./view-all-coupons-by-type.component.css']
})
export class ViewAllCouponsByTypeComponent implements OnInit {
  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);

  public companyCoupons:Coupon[]; 
  public erorr:string[] = [];

  constructor(private customerService:CustomerService ,private router:Router) { }

  ngOnInit() {

  }

  public showCoupons(){
    this.customerService.viewAllCompanyCouponsByType(this.coupon.type).subscribe(c=>{  
      console.log(c)
      this.companyCoupons = c;
    }, err => {

      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      }
      if( err.error.messages.find( (x: string) => x.includes("option",6)  ))  {
        this.erorr.push(" you have to choose a type")
      } 
      if( err.error.messages.find( (x: string) => x.includes("no coupons in this categorie for this customer")  ))  {
        this.erorr.push("no coupons in this categorie for this customer")
      } 

      

      alert(this.erorr)
      
      this.erorr.splice(0,this.erorr.length)


    })
  }

}
