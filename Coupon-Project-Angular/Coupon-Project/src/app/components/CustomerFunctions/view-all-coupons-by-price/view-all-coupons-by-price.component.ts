import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CustomerService } from 'src/app/Servises/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-coupons-by-price',
  templateUrl: './view-all-coupons-by-price.component.html',
  styleUrls: ['./view-all-coupons-by-price.component.css']
})
export class ViewAllCouponsByPriceComponent implements OnInit {

  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);

  public companyCoupons:Coupon[]; 
  public erorr:string[] = [];
  public price:number;

  constructor(private customerService:CustomerService ,private router:Router) { }

  ngOnInit() {

  }

  public showCoupons(){
    this.customerService.viewAllCompanyCouponsByPrice(this.price).subscribe(c=>{  
      console.log(c)
      this.companyCoupons = c;
    }, err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      }
      
      if( err.error.messages.find( (x: string) => x.includes("option",5)  ))  {
        this.erorr.push("You have to typein a price")
      } 
      if( err.error.messages.find( (x: string) => x.includes("date base validation")  ))  {
        this.erorr.push("no coupons with a price lower then this - " + this.price + " ,for this customer" )
      } 
      console.log(err.error)
      alert(this.erorr)
      
      this.erorr.splice(0,this.erorr.length)
    })
  }

}
