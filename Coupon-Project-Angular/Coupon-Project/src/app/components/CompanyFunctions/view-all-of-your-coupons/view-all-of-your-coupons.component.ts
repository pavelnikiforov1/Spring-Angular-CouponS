import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CompanyService } from 'src/app/Servises/company.service';
import { Router } from '@angular/router';




@Component({
  selector: 'app-view-all-of-your-coupons',
  templateUrl: './view-all-of-your-coupons.component.html',
  styleUrls: ['./view-all-of-your-coupons.component.css'],
  providers: []
})
export class ViewAllOfYourCouponsComponent implements OnInit {


  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);
  public erorr:string[] = [];
  public companyCoupons:Coupon[]; 

  public date:Date;

  constructor(private companyService:CompanyService ,private router:Router ) { }

  ngOnInit(  ) {
    
   
    this.companyService.viewAllCompanyCoupons().subscribe(c=>{  
      console.log(c)
      this.companyCoupons = c;
    }, err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      }

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
      console.log(err)
      if( err.error.messages.find( (x: string) => x.includes("End Date")  ))  {
        this.erorr.push(" End Date -  End Date must be a future date")
      } 
      if( err.error.messages.find( (x: string) => x.includes("one")  ))  {
        this.erorr.push(" Amount: must be greater than 0")
      } 

      alert(err.error.messages);
    })
  }
}
