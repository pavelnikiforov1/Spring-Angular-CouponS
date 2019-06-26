import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/entities/company';
import { Coupon } from 'src/app/entities/Coupon';
import { AdminService } from 'src/app/Servises/Admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-companies',
  templateUrl: './view-all-companies.component.html',
  styleUrls: ['./view-all-companies.component.css']
})
export class ViewAllCompaniesComponent implements OnInit {

  public company:Company = new Company(0,null,null,null);
  public companies: Company[];
  public companyCoupons: Coupon[];
  public coupon:Coupon = new Coupon(0,null,null,null,0,0,null,null,null,null,null);
  public erorr:string[] = [];

  public companyId:number = 0;

  constructor(private adminService:AdminService,private router:Router) { }

  ngOnInit() {
    this.adminService.viewAllCompanies().subscribe(c=>{
      this.companies = c;
    },err => {
      if(err.error.status == 401){
        alert("you are not logged in !");
        this.router.navigate(['login']);}
        else{
          if(err.error.messages.find( () =>  "no companies at the moment")){
            alert("no companies at the moment")
          }else{
            alert(err.error.messages)
          }    }
    })
  }

  public showCompanyCoupons(n:number){
    console.log(n)
    this.adminService.viewAllCompanyCoupons(n).subscribe(c=>{
      this.companyCoupons = c;
    },err => {
      if(err.error.messages.find( () =>  "no coupons for this company")){
        alert("no coupons for this company")
      } else {
      alert(err.error.messages)}
    })
  }

  public showCompany(company : Company){
   
    this.company = company;
  }

  public updateCompany(){
    this.adminService.updateCompany(this.company).subscribe(c=>{
      alert("Update Successful")
      this.router.navigate(['/adminPersonalArea'])
    }, err => {
      if( err.error.messages.find( (x: string) => x.includes("updateCompany.company.email: must be a well-formed email address")  ))  {
        this.erorr.push("one or more values you enter was not valide.  ")
        this.erorr.push(" Email -  must be a well-formed email address")
        
      } 
      if( err.error.messages.find( (x: string) => x.includes("Password size must be between 0 and 25")  ))  {
        this.erorr.push("Password size must be between 0 and 25 . yours is " + this.company.password.length )
      }
           console.log(err.error.messages)
      alert(this.erorr)
      
      this.erorr.splice(0,this.erorr.length);

      this.router.navigate(['/adminPersonalArea'])
    });
  }

  public deleteCompany(){
    this.adminService.deleteCompany(this.company.id).subscribe(c=>{
      alert("Successful In Deleting ")
      this.router.navigate(['/adminPersonalArea'])
    }, err => {

      alert(err.error.messages)

      this.router.navigate(['/adminPersonalArea'])
     
     

    });}
     




}
