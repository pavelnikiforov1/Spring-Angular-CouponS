import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/entities/Customer';
import { AdminService } from 'src/app/Servises/Admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

    public customer:Customer = new Customer(0,"","",null);
    public sec1:boolean = false;
    public sec2:boolean = false;
    public erorr:string[] = []; 

  constructor(private adminService:AdminService, private router:Router) { }

  ngOnInit() {
  }

  public creatCustomer(){
    this.adminService.createCustomer(this.customer).subscribe(c=>{
     alert("Customer Created")
    }, err =>{
  
    if(err.error.status == 401){
      alert("you are not logged in !");
      this.router.navigate(['login']);}
    
    if( err.error.messages.find( (x: string) => x.includes("base",2)  ))  {
      this.erorr.push("customer name allredy exist :" + this.customer.custName )
    }
    if( err.error.messages.find( (x: string) => x.includes("createCustomer.customer.password")  ))  {
      this.erorr.push("Password size must be between 0 and 25 . yours is " + this.customer.password.length )
    }

    alert(this.erorr)
  })
  
  
  this.erorr.splice(0,this.erorr.length);
  

}

}