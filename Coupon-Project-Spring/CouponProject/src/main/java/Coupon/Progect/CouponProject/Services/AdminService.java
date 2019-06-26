package Coupon.Progect.CouponProject.Services;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;



/**
 * Interface class that contains all of the relevant ADMIN SERVICE method signature
 * uses the validated Variant to validated all parameters provided.
 * @author pavel
 *
 */
@Validated
public interface AdminService {

	public void createCompany(@Valid Company company) throws CustomValidationExepation, CustomDateBaseExepation;

	public void deleteCompany(@Positive int companyId) ;

	public void updateCompany(@Valid Company company) throws CustomValidationExepation, CustomDateBaseExepation;

	public Company getCompany(@Positive int CompanyId) throws CustomDateBaseExepation;

	public List<Company> getAllCompanies() throws CustomDateBaseExepation;

	public List<Coupon> getAllCompanyCoupons( @Positive int compId) throws CustomDateBaseExepation;
	
	public void createCustomer(@Valid Customer customer) throws CustomValidationExepation, CustomDateBaseExepation;

	public void removeCustomer(@Positive int customerId) ;

	public void updateCustomer(@Valid Customer customer) throws CustomValidationExepation, CustomDateBaseExepation;

	public Customer getCustomer(@Positive int CustomerId) throws CustomDateBaseExepation;

	public List<Customer> getAllCustomer() throws  CustomDateBaseExepation;
	
	List<Coupon> getAllCustomerCoupons(@Positive int custId) throws CustomDateBaseExepation;

	public LoggedUser login(@Valid String userName,@Valid String password,ClientType clientType) throws CustomLoginExeption;

}
