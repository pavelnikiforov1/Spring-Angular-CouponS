package Coupon.Progect.CouponProject.validations;

import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.ripositories.CompanyRipository;
import Coupon.Progect.CouponProject.ripositories.CouponRipository;
import Coupon.Progect.CouponProject.ripositories.CustomerRipository;
import net.bytebuddy.implementation.bytecode.Throw;



/**
 * A Validation Class containing 3 validation methods being used by the different services.
 * <h2>data members</h2>
 * - CompanyRipository <br>
 * - CustomerRipository<br>
 * - CouponRipository<br>
 * @author pavel
 *
 */
@Component
public class Validations {

	@Autowired
	private CompanyRipository companyRipository;
	@Autowired
	private CouponRipository couponRipository;
	@Autowired
	private CustomerRipository customerRipository;

	
	/**
	 * <h3>Validate Coupon</h3>
	 * A method that validates a coupon in two situations : <br>
	 * 1) when the "Status" is <b>create</b> - <br> 
	 * will check if title is available, will check if this company already has this coupon ,
	 * will check if the start date is a valid date , will check if end date is a valid date. <br>
	 * 2) when the "Status" is <b>ownership</b> - <br>
	 * will check if there is a coupon with this title ,
	 * will check if this coupon belongs to this company ,
	 * will check if the endDate is a valid date.
	 * 
	 * @param coupon - (Coupon)
	 * @param id - company ID (int)
	 * @param status - (String)
	 * @throws CustomValidationExepation - in case on of the parameters is not valid.
	 * @throws CustomDateBaseExepation - in case this coupon already exist or do not belong to this company .
	 */
	public void validateCoupon(Coupon coupon, int id, String status) throws CustomValidationExepation, CustomDateBaseExepation {
		switch (status) {
		case "create":
				System.out.println(couponRipository.findByTitle(coupon.getTitle()));
			if (!(couponRipository.findByTitle(coupon.getTitle()).isEmpty())) {
				throw new CustomDateBaseExepation("coupon title already exist " + coupon.getTitle());
			}
			
			if (couponRipository.findByCompanyId(id, coupon.getId()).contains(coupon)) {
				throw new CustomDateBaseExepation("you allredy have this coupon");
			}
			
			if(coupon.getStartDate().before(new Date()) ) {
				throw new CustomValidationExepation("Start Date must be a future date ");
			}
			if(  !( coupon.getStartDate().before(coupon.getEndDate() ) ) ) {
				throw new CustomValidationExepation("Start Date Cannot be after End Date ");
			}
			
			
			break;
		case "ownership":
			if (couponRipository.findByTitle(coupon.getTitle()) == null) {
				throw new CustomDateBaseExepation("no coupon with this title " + coupon.getTitle());
			}
			List<Coupon> c = couponRipository.findByCompanyId(id, coupon.getId());
			if (c.get(0).getId() != coupon.getId()) {
				throw new CustomDateBaseExepation("this is not your coupon" + coupon.getTitle());
			}
			if(coupon.getEndDate().before(new Date()) ) {
				throw new CustomValidationExepation("End Date must be a future date ");
			}
			break;
			
		}
	}
	
	/**
	 	 * <h3>Validate Company</h3>
	 * A method that validates a company in two situations : <br>
	 * 1) when the "Status" is <b>create</b> - <br> 
	 * will check if this company exist . <br>
	 * 2) when the "Status" is <b>update</b> - <br>
	 * will check if this company do not exist
	 *  
	 * @param company - (Comapany)
	 * @param status - (String)
	 * @throws CustomDateBaseExepation - in the first case company already exist and in the second case it do not. 
	 */
	public void validatCompany(Company company, String status) throws  CustomDateBaseExepation {

		switch (status) {
		case "create":
			if (companyRipository.findById(company.getId()).isPresent()) {
				throw new CustomDateBaseExepation("company is allredy existing " + company.getName());
			}
			break;

		case "update":

			if (!companyRipository.findById(company.getId()).isPresent()) {
				throw new CustomDateBaseExepation("company do not exist " + company.getName());
			}
			break;
		}
	}

	/**
	 * <h3>Validate Customer</h3>
	 * A method that validates a customer in two situations : <br>
	 * 1) when the "Status" is <b>create</b> - <br> 
	 * will check if this customer exist . <br>
	 * 2) when the "Status" is <b>update</b> - <br>
	 * will check if this customer do not exist .
	 * @param customer - (Customer)
	 * @param status - (String)
	 * @throws CustomDateBaseExepation - in the first case customer already exist and in the second case it do not. 
	 */
	public void validatCustomer(Customer customer, String status) throws CustomDateBaseExepation {

		switch (status) {
		case "create":
			if (customerRipository.findById(customer.getId()).isPresent()) {
				throw new CustomDateBaseExepation("customer name already exist " + customer.getCustName());
			}
			break;

		case "update":

			if (!customerRipository.findById(customer.getId()).isPresent()) {
				throw new CustomDateBaseExepation("customer name do not exist " + customer.getCustName());
			}
			break;
		}
	}

	
	}


