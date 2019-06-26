import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/entities/Coupon';
import { CompanyService } from 'src/app/Servises/company.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-creat-coupon',
  templateUrl: './creat-coupon.component.html',
  styleUrls: ['./creat-coupon.component.css']
})
export class CreatCouponComponent implements OnInit {
  
  
  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);
  public year = null;
  public month = null;
  public day = null;
  public date= null;
  public erorr:string[] = [];
  

  constructor(private companyService:CompanyService, private router:Router ) { }

  ngOnInit() {
    this.year = new Date().getFullYear().valueOf()
    this.month = new Date().getMonth().valueOf()
    this.day = new Date().getDay().valueOf() + 16;
    this.date = new Date().setFullYear(this.year,this.month,this.day).valueOf()
    
  }

  public createCoupon(){
    console.log(this.coupon)
    

    this.companyService.createCoupon(this.coupon).subscribe(()=>{
      alert("secsess")
    } , err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);
      }
      console.log(err.error.messages)
      
    if( err.error.messages.find( (x: string) => x.includes("Start Date")  ))  {
      this.erorr.push("one or more values you enter was not valide.  ")
      this.erorr.push(" Start Date -  Start Date must be a future date")
    } 
    if( err.error.messages.find( (x: string) => x.includes("createCoupon.coupon.amount:")  ))  {
      this.erorr.push(" Amount: must be greater than 0")
    } 
    if( err.error.messages.find( (x: string) => x.includes("createCoupon.coupon.price")  ))  {
      this.erorr.push(" Price: must be greater than 0")
    } 
    if( err.error.messages.find( (x: string) => x.includes("date base validation - coupon title already exist")  ))  {
      this.erorr.push("coupon title already exist :" + this.coupon.title )
    }
    if( err.error.messages.find( (x: string) => x.includes("createCoupon.coupon.type")  ))  {
      this.erorr.push("You must choose a type")
    }
    if( err.error.messages.find( (x: string) => x.includes("size must be between 0 and 25")  ))  {
      this.erorr.push("Password size must be between 0 and 25 . yours is "  )
    }else
      alert(this.erorr)

      this.erorr.splice(0,this.erorr.length)

    })
  }

}

