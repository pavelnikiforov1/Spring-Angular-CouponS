import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CompanyService } from 'src/app/Servises/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-of-your-coupons-by-date',
  templateUrl: './view-all-of-your-coupons-by-date.component.html',
  styleUrls: ['./view-all-of-your-coupons-by-date.component.css']
})
export class ViewAllOfYourCouponsByDateComponent implements OnInit {

  
 
  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);

  public companyCoupons:Coupon[]; 
  public erorr:string[] = [];
  
  public newCoupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);


  constructor(private companyService:CompanyService ,private router:Router) { }

  ngOnInit() {

  }

  public showCoupons(){
    console.log(this.newCoupon.endDate)
    this.companyService.viewAllCompanyCouponsByDate(this.newCoupon.endDate).subscribe(c=>{  
      console.log(c)
      this.companyCoupons = c;
    }, err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      }
      console.log(err.error)
      if( err.error.messages.find( (x: string) => x.includes("option",5)  ))  {
        this.erorr.push("You have to Choose a Date")
      } 
      if( err.error.messages.find( (x: string) => x.includes("date base validation - no coupons with")  ))  {
        this.erorr.push("no coupons with a expertion date prior to this - " + this.newCoupon.endDate + " ,for this company" )
      } 
      console.log(err.error)
      alert(this.erorr)
      
      this.erorr.splice(0,this.erorr.length)
    })
  }

}
