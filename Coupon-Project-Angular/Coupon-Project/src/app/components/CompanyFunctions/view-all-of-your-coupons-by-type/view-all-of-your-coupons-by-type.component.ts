import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CompanyService } from 'src/app/Servises/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-of-your-coupons-by-type',
  templateUrl: './view-all-of-your-coupons-by-type.component.html',
  styleUrls: ['./view-all-of-your-coupons-by-type.component.css']
})
export class ViewAllOfYourCouponsByTypeComponent implements OnInit {

  
  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);

  public companyCoupons:Coupon[]; 
 
  public erorr:string[] = [];

  constructor(private companyService:CompanyService ,private router:Router) { }

  ngOnInit() {
    
  }

  public showCoupons(){
    this.companyService.viewAllCompanyCouponsBytype(this.coupon.type).subscribe(c=>{  
      console.log(c)
      this.companyCoupons = c;
    }, err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      } 

      if( err.error.messages.find( (x: string) => x.includes("you have to choose a type")  ))  {
        this.erorr.push(" you have to choose a type")
      } 
      
      if( err.error.messages.find( (x: string) => x.includes("no coupons with this type")  ))  {
        this.erorr.push("no coupons with this type")
      }  
      alert(this.erorr)
      this.erorr.splice(0,this.erorr.length);
    })
  }

  public showCoupon(coupon:Coupon){

    this.coupon = coupon;

  }


  public deleteCoupon(couponId:number){
    alert(couponId)
    this.companyService.deleteCoupon(couponId).subscribe(() => {
      alert("secsses")
      this.router.navigate(["comapnyPersonalArea"]);
    }, err => {
      alert(err.error.messages);
    })

  }


  public updateCoupon(){
    this.companyService.updateCoupon(this.coupon).subscribe(c=>{
      alert("secsess")
    } , err => {
      alert(err.error.messages);
    })
  }
}
