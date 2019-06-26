import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/entities/company';
import { AdminService } from 'src/app/Servises/Admin.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

public company:Company = new Company(0,null,null,null);

public erorr:string[] = [];


  constructor(private adminService:AdminService, private router:Router) { }

  ngOnInit() {
  }

  public createCompany(){
    this.adminService.createCompany(this.company).subscribe(c=>{
      alert("Company Created")
    }, err => {
    console.log(err.error)
    if(err.error.status == 401){
      alert("you are not logged in !");
      this.router.navigate(['login']);}

    if( err.error.messages.find( (x: string) => x.includes("createCompany.company.email:")  ))  {
      this.erorr.push("one or more values you enter was not valide.  ")
      this.erorr.push(" Email -  must be a well-formed email address")
    } 
    
    if( err.error.messages.find( (x: string) => x.includes("company name is allredy exist")  ))  {
      this.erorr.push("company name allredy exist :" + this.company.name )
    }
    if( err.error.messages.find( (x: string) => x.includes("size must be between 0 and 25")  ))  {
      this.erorr.push("Password size must be between 0 and 25 . yours is " + this.company.password.length )
    }
    
    alert(this.erorr)
    
  }
    
  );
  this.erorr.splice(0,this.erorr.length);
}


}
