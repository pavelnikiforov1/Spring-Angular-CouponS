import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './components/layout/layout.component';
import { IntroPageComponent } from './components/intro-page/intro-page.component';
import { MainComponent } from './main/main.component';
import { HederComponent } from './components/heder/heder.component';
import { FooterComponent } from './components/footer/footer.component';
import { CouponRowPreviewComponent } from './components/coupon-row-preview/coupon-row-preview.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FormsModule } from '@angular/forms';
import{HttpClientModule} from "@angular/common/http";
import { CompanyComponent } from './components/company/company.component';
import { CreatCouponComponent } from './components/CompanyFunctions/creat-coupon/creat-coupon.component';
import { MainPageComponent } from './components/CompanyFunctions/main-page/main-page.component';
import { ViewAllOfYourCouponsComponent } from './components/CompanyFunctions/view-all-of-your-coupons/view-all-of-your-coupons.component';
import { AdminComponent } from './components/admin/admin.component';
import { CreateCompanyComponent } from './components/AdminFunctions/create-company/create-company.component';
import { ViewAllCompaniesComponent } from './components/AdminFunctions/view-all-companies/view-all-companies.component';
import { CreateCustomerComponent } from './components/AdminFunctions/create-customer/create-customer.component';
import { ViewAllCustomersComponent } from './components/AdminFunctions/view-all-customers/view-all-customers.component';
import { CustomerComponent } from './components/customer/customer.component';
import { ViewAllCouponsComponent } from './components/CustomerFunctions/view-all-coupons/view-all-coupons.component'
import { ViewAllOfYourCouponsByTypeComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-type/view-all-of-your-coupons-by-type.component';
import { ViewAllCouponsByTypeComponent } from './components/CustomerFunctions/view-all-coupons-by-type/view-all-coupons-by-type.component';
import { ViewAllCouponsByPriceComponent } from './components/CustomerFunctions/view-all-coupons-by-price/view-all-coupons-by-price.component';
import { CouponPreviewELECTRICITYComponent } from './components/UtilityFuncations/coupon-preview-electricity/coupon-preview-electricity.component';
import { CouponPreviewHEALTHComponent } from './components/UtilityFuncations/coupon-preview-health/coupon-preview-health.component';
import { CouponPreviewRESTURANSComponent } from './components/UtilityFuncations/coupon-preview-resturans/coupon-preview-resturans.component';
import { AllCouponsComponent } from './components/Categories/all-coupons/all-coupons.component';
import { RestaurantCouponsComponent } from './components/Categories/restaurant-coupons/restaurant-coupons.component';
import { ELECTRICITyCouponsComponent } from './components/Categories/electricity-coupons/electricity-coupons.component';
import { HEALThCouponsComponent } from './components/Categories/health-coupons/health-coupons.component';
import { ViewAllOfYourCouponsByPriceComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-price/view-all-of-your-coupons-by-price.component';
import { ViewAllOfYourCouponsByDateComponent } from './components/CompanyFunctions/view-all-of-your-coupons-by-date/view-all-of-your-coupons-by-date.component';
import { CustomerMainComponent } from './components/CustomerFunctions/customer-main/customer-main.component';
import { AdminMainPageComponent } from './components/AdminFunctions/admin-main-page/admin-main-page.component';
import { Page404Component } from './page404/page404.component';
import { ServerIsDownPageComponent } from './components/server-is-down-page/server-is-down-page.component';


 
@NgModule({
  declarations: [
  LayoutComponent,
  IntroPageComponent,
  MainComponent,
  HederComponent,
  FooterComponent,
  CouponRowPreviewComponent,
  LoginPageComponent,
  CompanyComponent,
  CreatCouponComponent,
  MainPageComponent,
  ViewAllOfYourCouponsComponent,
  AdminComponent,
  CreateCompanyComponent,
  ViewAllCompaniesComponent,
  CreateCustomerComponent,
  ViewAllCustomersComponent,
  CustomerComponent,
  ViewAllCouponsComponent,
  ViewAllOfYourCouponsByTypeComponent,
  ViewAllCouponsByTypeComponent,
  ViewAllCouponsByPriceComponent,
  CouponPreviewELECTRICITYComponent,
  CouponPreviewHEALTHComponent,
  CouponPreviewRESTURANSComponent,
  AllCouponsComponent,
  RestaurantCouponsComponent,
  ELECTRICITyCouponsComponent,
  HEALThCouponsComponent,
  ViewAllOfYourCouponsByPriceComponent,
  ViewAllOfYourCouponsByDateComponent,
  CustomerMainComponent,
  AdminMainPageComponent,
  Page404Component,
  ServerIsDownPageComponent,

 

  
 ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,HttpClientModule,
  ],
  providers: [],
  bootstrap: [LayoutComponent]
})
export class AppModule { }
