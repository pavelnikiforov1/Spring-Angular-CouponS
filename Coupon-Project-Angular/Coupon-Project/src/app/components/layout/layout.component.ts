import { Component, OnInit } from '@angular/core';
import { LogginService } from 'src/app/Servises/loggin.service';
import { LoggedUser } from 'src/app/entities/logged-user';
import { Router } from '@angular/router';
import { CheckConService } from 'src/app/Servises/check-con.service';


@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  constructor(private check:CheckConService,private router:Router) { }

  ngOnInit() {

    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });

  }

}
