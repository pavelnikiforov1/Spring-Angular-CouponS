import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/entities/Customer';
import { Coupon } from 'src/app/entities/Coupon';
import { AdminService } from 'src/app/Servises/Admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-customers',
  templateUrl: './view-all-customers.component.html',
  styleUrls: ['./view-all-customers.component.css']
})
export class ViewAllCustomersComponent implements OnInit {
  
  public customer:Customer = new Customer(0,null,null,null)
  public customers: Customer[];
  public customerCoupons: Coupon[];

  
  public customerId:number = 0;

  constructor(private adminService:AdminService, private router:Router) { }

  ngOnInit() {
    
    this.adminService.viewAllCustomers().subscribe(c=>{
      this.customers = c;
    },err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);}
        else{
          if(err.error.messages.find( () =>  "no customer at the moment")){
            alert("no customer at the moment")
          }else{
            alert(err.error.messages)
          }    }})
  }

  public showCustomerCoupons(n:number){
    console.log(n)
    this.adminService.viewAllCustomerCoupons(n).subscribe(c=>{
      console.log(c)
      this.customerCoupons = c;
    },err => { if(err.error.messages.find( () =>  "no coupons for this customer")){
      alert("no coupons for this customer")
    } else {
    alert(err.error.messages)}
  })
  }


  public showCustomer(customer : Customer){
   
    this.customer = customer;
    console.log(this.customer)
  }

  
  public deleteCustomerr(){
    console.log(this.customer.id)
    this.adminService.deleteCustomer(this.customer).subscribe( c => {
      alert("Successful In Deleting ")
      this.router.navigate(['/adminPersonalArea/viewAllOfYourCustomers'])
    }, err => {
      alert(err.error.messages)
    })
  }

  public updateCustomer(){
    this.adminService.updateCustomer(this.customer).subscribe(c=>{
      alert("Customer Updated")
    }, err => {
      if(err.error.messages.find( () =>  "updateCustomer.customer.password")){
        alert("Password size must be between 0 and 15 yours was " + this.customer.password.length)

        this.router.navigate(['/adminPersonalArea/viewAllOfYourCustomers'])
      } else{
      alert(err.error.messages)   }
    });
  }
}
