import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { CompanyComponent } from './components/company/company.component';
import { CreatCouponComponent } from './components/CompanyFunctions/creat-coupon/creat-coupon.component';
import { MainPageComponent } from './components/CompanyFunctions/main-page/main-page.component';

import { ViewAllOfYourCouponsComponent } from './components/CompanyFunctions/view-all-of-your-coupons/view-all-of-your-coupons.component';
import { CreateCompanyComponent } from './components/AdminFunctions/create-company/create-company.component';
import { ViewAllCompaniesComponent } from './components/AdminFunctions/view-all-companies/view-all-companies.component';
import { CreateCustomerComponent } from './components/AdminFunctions/create-customer/create-customer.component';
import { ViewAllCustomersComponent } from './components/AdminFunctions/view-all-customers/view-all-customers.component';
import { CustomerComponent } from './components/customer/customer.component';
import { ViewAllCouponsComponent } from './components/CustomerFunctions/view-all-coupons/view-all-coupons.component';
import { AdminComponent } from './components/admin/admin.component';
import { ViewAllOfYourCouponsByTypeComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-type/view-all-of-your-coupons-by-type.component';
import { ViewAllCouponsByTypeComponent } from './components/CustomerFunctions/view-all-coupons-by-type/view-all-coupons-by-type.component';
import { ViewAllCouponsByPriceComponent } from './components/CustomerFunctions/view-all-coupons-by-price/view-all-coupons-by-price.component';
import { AllCouponsComponent } from './components/Categories/all-coupons/all-coupons.component';
import { RestaurantCouponsComponent } from './components/Categories/restaurant-coupons/restaurant-coupons.component';
import { HEALThCouponsComponent } from './components/Categories/health-coupons/health-coupons.component';
import { ELECTRICITyCouponsComponent } from './components/Categories/electricity-coupons/electricity-coupons.component';
import { CouponRowPreviewComponent } from './components/coupon-row-preview/coupon-row-preview.component';
import { ViewAllOfYourCouponsByPriceComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-price/view-all-of-your-coupons-by-price.component';
import { ViewAllOfYourCouponsByDateComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-date/view-all-of-your-coupons-by-date.component';
import { CustomerMainComponent } from './components/CustomerFunctions/customer-main/customer-main.component';
import { AdminMainPageComponent } from './components/AdminFunctions/admin-main-page/admin-main-page.component';
import { Page404Component } from './page404/page404.component';
import { ServerIsDownPageComponent } from './components/server-is-down-page/server-is-down-page.component';

const routes: Routes = [
  {path:"login",component:LoginPageComponent},
  {path:"home", component: MainComponent,children: [
    {
      path:"",
      component:CouponRowPreviewComponent
    },
  {
    path:"allCoupons",
    component:AllCouponsComponent
  },
  {
    path:"restaurantCoupons",
    component:RestaurantCouponsComponent
  },
  {
    path:"healthCoupons",
    component:HEALThCouponsComponent
  },
  {
    path:"electricityCoupons",
    component:ELECTRICITyCouponsComponent
  },
  {path:"**",component:Page404Component,pathMatch:"full"}
  
  ]},
  {path:"comapnyPersonalArea" ,component:CompanyComponent,children: [
    {
      path:"",
      component:MainPageComponent
    },
    {
    path: 'createCoupon',
    component:  CreatCouponComponent
  },{
    path:'viewAllOfYourCoupons',
    component:ViewAllOfYourCouponsComponent
  },{
    path:'viewAllOfYourCouponsByType',
    component:ViewAllOfYourCouponsByTypeComponent
  },{
    path:'viewAllOfYourCouponsByPrice',
    component:ViewAllOfYourCouponsByPriceComponent
  },{
    path:'viewAllOfYourCouponsByDate',
    component:ViewAllOfYourCouponsByDateComponent
  },{
    path:"**",
    component:MainPageComponent
  }
]},
  {path:"adminPersonalArea",component:AdminComponent,children: [
    {
      path:"",
      component:AdminMainPageComponent
    },
    {
    path: 'createCompany',
    component:CreateCompanyComponent
  },{
    path:'viewAllOfYourCompanies',
    component:ViewAllCompaniesComponent
  },{
    path: 'createCustomer',
    component:  CreateCustomerComponent
  },{
    path:'viewAllCustomers',
    component:ViewAllCustomersComponent
  },{
    path:"**",
    component:AdminMainPageComponent
  }
]},
{path:"customerPersonalArea",component:CustomerComponent,children: [
  {
    path:"",
    component:CustomerMainComponent
  },
  {
  path: 'viewAllCoupons',
  component:  ViewAllCouponsComponent
},
{ path: 'viewAllCouponsByType',
component:  ViewAllCouponsByTypeComponent}
,{path: 'viewAllCouponsByPrice',
component:  ViewAllCouponsByPriceComponent}
,{
  path:"**",
  component:CustomerMainComponent
}
]},
  {path:"404",component:Page404Component},{path:"serverIsDown",component:ServerIsDownPageComponent},
  {path:"**",component:Page404Component,pathMatch:"full"},
  
  
  
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
