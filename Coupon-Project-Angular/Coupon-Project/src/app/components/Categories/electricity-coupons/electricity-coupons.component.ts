import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { LoggedUser } from 'src/app/entities/logged-user';
import { UtilityService } from 'src/app/Servises/utility.service';
import { LogginService } from 'src/app/Servises/loggin.service';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/Servises/customer.service';

@Component({
  selector: 'app-electricity-coupons',
  templateUrl: './electricity-coupons.component.html',
  styleUrls: ['./electricity-coupons.component.css']
})
export class ELECTRICITyCouponsComponent implements OnInit {

  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);
  public coupons:Coupon[];
  public type:string = "ELECTRICITY";
  public loggedUser:LoggedUser  = new LoggedUser(0,null,null,null); 
  public bestSellarCoupon1:Coupon;
  public bestSellarCoupon2:Coupon;
  public erorr:string[] = [];

  constructor(private utilityServise:UtilityService , private logginService:LogginService , private router:Router ,private customerService:CustomerService) { }

  ngOnInit() {
    this.utilityServise.getAllCouponsWhereTypeIsELECTRICITYInCategorie().subscribe(c =>{
      this.coupons = c ;
      var amount:number = 0;
       var dlt:number = 0 ;
      console.log(this.coupons)
      for (let index = 0; index < c.length; index++) {
      if( c[index].amount > amount ){
        this.bestSellarCoupon1 = c[index];
       dlt = index;
      } 
      
      }
      this.coupons.splice(dlt)
      console.log(this.coupons)
      for (let index = 0; index < c.length; index++) {
        if( c[index].amount > amount && c[index] != this.bestSellarCoupon1 ){
          this.bestSellarCoupon2 = c[index];
          dlt = index;
        } 
        
        }

        this.coupons.splice(dlt)

      console.log(this.coupons)
    } ,err => {


      console.log(err.error.messages)
    })
  }


  public showCoupon(coupon:Coupon){

    this.coupon = coupon;

  }

  public PurchaseCoupon(id:number){

    this.loggedUser = this.logginService.getLoggduser();

    if(this.loggedUser.id == 0){
      alert("you are not logged in ")
      this.router.navigate(["login"]);
    } else if(this.loggedUser.clientType === "CUSTOMER"){
      this.customerService.purchaseCoupon(id).subscribe(c=>{
        
        alert("coupon Purchesd")
      } , error => {
        
        
        if( error.error.messages.find( (x: string) => x.includes("you allredy have this coupon")  ))  {
          this.erorr.push("you allredy have this coupon")
        } 
        alert(this.erorr)
       

        this.erorr.splice(0,this.erorr.length)
      })
    }else{alert("Opps You are not a Customer")}

  
  }

}

