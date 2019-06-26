import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { CheckConService } from 'src/app/Servises/check-con.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor( private router:Router,private check:CheckConService) { }

  ngOnInit() {
    this.check.checkCon().subscribe(()=>{
     
    }, () =>{
      this.router.navigate(["serverIsDown"]);
    });
   }

  }


