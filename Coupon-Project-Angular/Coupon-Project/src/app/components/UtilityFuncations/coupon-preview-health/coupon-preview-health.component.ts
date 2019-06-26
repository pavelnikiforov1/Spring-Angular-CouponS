import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { LoggedUser } from 'src/app/entities/logged-user';
import { UtilityService } from 'src/app/Servises/utility.service';
import { LogginService } from 'src/app/Servises/loggin.service';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/Servises/customer.service';

@Component({
  selector: 'app-coupon-preview-health',
  templateUrl: './coupon-preview-health.component.html',
  styleUrls: ['./coupon-preview-health.component.css']
})
export class CouponPreviewHEALTHComponent implements OnInit {

  public healthCoupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);
  public coupons:Coupon[];
  public type:string = "HEALTH"
  public loggedUser:LoggedUser  = new LoggedUser(0,null,null,null); 
  public erorr:string[] = [];
 

  constructor(private utilityServise:UtilityService , private logginService:LogginService , private router:Router ,private customerService:CustomerService) { }

  ngOnInit() {
    this.utilityServise.getAllCouponsWhereTypeIsHEALTH().subscribe(c =>{
      this.coupons = c ;
      
    } ,err => {
      console.log(err)
    })
  }


  public showHEALTHCoupon(coupon:Coupon){
    
    this.healthCoupon = coupon;
    
  }

  public PurchaseCoupon(id:number){

    this.loggedUser = this.logginService.getLoggduser();

    if(this.loggedUser.id == 0){
     alert("you are not logged in")
      this.router.navigate(["login"]);
    }else if(this.loggedUser.clientType === "CUSTOMER"){
      this.customerService.purchaseCoupon(id).subscribe(c=>{
        
        alert("coupon Purchesd")
      } , error => {
        console.log(error.error);
        
        if( error.error.messages.find( (x: string) => x.includes("you allredy have this coupon")  ))  {
          this.erorr.push("you allredy have this coupon")
          alert(this.erorr)
        } 
       
        
      })
    }else{alert("Opps You are not a Customer")}

    

    

    
  }

}
