package Coupon.Progect.CouponProject.Services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.ripositories.CompanyRipository;
import Coupon.Progect.CouponProject.ripositories.CouponRipository;
import Coupon.Progect.CouponProject.ripositories.CustomerRipository;
import Coupon.Progect.CouponProject.validations.Validations;

/**
 * Service Class that implements all of the AdminService methods
 * <h2>data members</h2>
 * - CompanyRipository <br>
 * - CustomerRipository<br>
 * - CouponRipository<br>
 * - Validations 
 * @author pavel
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CompanyRipository companyRipository;
	@Autowired
	private CustomerRipository customerRipository;
	@Autowired
	private CouponRipository couponRipository;
	@Autowired
	private Validations validations;

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "1234";

	/**
	 * <h3>Create Company</h3> A method that receives a company validates it by the
	 * Validation.Class and and uses the CompanyRipository.Class to Save it in the date base
	 * 
	 * @param company - Company Entity (Company)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base
	 */
	public void createCompany(Company company) throws CustomValidationExepation, CustomDateBaseExepation {

		String status = "create";
		
		validations.validatCompany(company, status);

		companyRipository.save(company);

	}

	
	/**
	 * <h3>Update Company</h3> A method that receives a company validates it by the
	 * Validation.Class and uses the CompanyRipository.Class to update it .
	 * 
	 * @param company - Company Entity (Company)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base
	 */
	@Override
	public void updateCompany(Company company) throws CustomValidationExepation, CustomDateBaseExepation {
		String status = "update";

		validations.validatCompany(company, status);

		companyRipository.updateCompany(company.getPassword(), company.getEmail(), company.getId());
	}
	
	
	/**
	 * <h3>Delete Company</h3> A method that receives a ID of a company to be deleted
	 * first deletes all of its coupons then deletes the company
	 * in case one of the methods fail will perform rollBack 
	 * @param companyId - company ID Entity  (int)                        
	 */
	@Transactional
	@Override
	public void deleteCompany(int companyId) {
		
		couponRipository.removeCoupons(companyId);
		companyRipository.deleteById(companyId);

	}

	/**
	 * <h3>Get Company</h3> A method that receives a company id 
	 * uses CompanyRipository.Class to find the company  .
	 * 
	 * @param companyId - company ID (int)
	 * @throws CustomDateBaseExepation - in case there is no company with this ID
	 * @return Optional(Company)
	 */
	@Override
	public Company getCompany(int companyId) throws CustomDateBaseExepation {

		Optional<Company> c = companyRipository.findById(companyId);

		if (!c.isPresent()) {
			throw new CustomDateBaseExepation("no Company with this id " + companyId);
		}
		return c.get();
	}
	
	/**
	 * <h3>Get All Companies</h3> A method that  
	 * uses CompanyRipository.Class to find the all company currently in the data base .
	 * 
	 * @throws CustomDateBaseExepation - in case there is no companies at the data base.
	 * @return List(Company)
	 */
	@Override
	public List<Company> getAllCompanies() throws CustomDateBaseExepation {

		List<Company> c = companyRipository.findAll();

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no companies at this moment");
		}
		return c;
	}
	
	/**
	 * <h3>Get All Company Coupons</h3> A method that  
	 * uses CompanyRipository.Class to find the all the coupons for a given company currently in the data base .
	 * 
	 * @param compId = company ID (int)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this company at the data base.
	 * @return List(Company)
	 */
	@Override
	public List<Coupon> getAllCompanyCoupons(int compId) throws CustomDateBaseExepation {

		List<Coupon> c = couponRipository.findAllById(compId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons for this company ");
		}

		return c;
	}
	
	/**
	 * <h3>Create Customer</h3> A method that receives a customer validates it by the
	 * Validation.Class and Saves it in the date base
	 * 
	 * @param customer - Customer Entity (Customer)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base
	 */
	@Override
	public void createCustomer(Customer customer) throws CustomValidationExepation, CustomDateBaseExepation {
		String status = "create";
		validations.validatCustomer(customer, status);

		customerRipository.save(customer);

	}
	
	/**
	 * <h3>Update Customer</h3> A method that receives a customer validates it by the
	 * Validation.Class and uses the CustomerRipository.Class to update it .
	 * 
	 * @param customer - Customer Entity (Customer)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base
	 */
	@Override
	public void updateCustomer(Customer customer) throws CustomValidationExepation, CustomDateBaseExepation {
		String status = "update";
		validations.validatCustomer(customer, status);

		customerRipository.updateCustomer(customer.getPassword(), customer.getId());

	}
	
	/**
	 * <h3>Remove Customer</h3> A method that receives a ID of a customer to be deleted
	 * and deletes and his coupons 
	 * @param customerId - customer ID (int)                  
	 */
	@Override
	public void removeCustomer(int customerId) {
	
		customerRipository.deleteById(customerId);
		
	}
	/**
	 * <h3>Get Customer</h3> A method that receives a customers id 
	 * uses CustomerRipository.Class to find the customer  .
	 * 
	 * @param customerId - company ID (int)
	 * @throws CustomDateBaseExepation - in case there is no company with this ID
	 * @return Optional(Customer)
	 */
	@Override
	public Customer getCustomer(int customerId) throws CustomDateBaseExepation {
		Optional<Customer> c = customerRipository.findById(customerId);

		if (c == null) {
			throw new CustomDateBaseExepation("no Customer with this id " + customerId);
		}
		return c.get();
	}
	
	
	/**
	 * <h3>Get All Customers</h3> A method that  
	 * uses CustomerRipository.Class to find the all customers currently in the data base .
	 * 
	 * @throws CustomDateBaseExepation - in case there is no customers at the data base.
	 * @return List(Company)
	 */
	@Override
	public List<Customer> getAllCustomer() throws CustomDateBaseExepation {
		List<Customer> c = customerRipository.findAll();

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no customers at this moment");
		}
		return c;
	}
	
	/**
	 * <h3>Get All Customer Coupons</h3> A method that  
	 * uses CompanyRipository.Class to find the all the coupons for a given customer currently in the data base .
	 * 
	 * @param custId - customer ID (int)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this customer at the data base.
	 * @return List(Company)
	 */
	@Override
	public List<Coupon> getAllCustomerCoupons(int custId) throws CustomDateBaseExepation {

		List<Coupon> c = customerRipository.getAllPurchasedCoupons(custId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons for this Customer ");
		}

		return c;
	}

	/**
	 * <h3>Login</h3>
	 * 	this method uses the Class Data Members and performs a comparison to the parameters .
	 * @param userName - the user name of the client (String)
	 * @param password - the password of the client (String)
	 * @param clientType - the client Type of the client (ClientType)
	 * 
	 * @throws CustomLoginExeption - in case one of the parameters is not correct.
	 * @return new LoggedUser with default parameters
	 */
	@Override
	public LoggedUser login(String userName, String password, ClientType clientType)
			throws  CustomLoginExeption {

		if (!userName.trim().equals(AdminServiceImpl.USERNAME.trim())) {
			throw new CustomLoginExeption("User-Name - :" + userName + " or Password - " + password + " or Type -" +clientType);
		}

		if (!password.equals(AdminServiceImpl.PASSWORD)) {
			throw new CustomLoginExeption("User-Name - :" + userName + " or Password - " + password + " or Type - " +clientType);
		}
		if (!clientType.equals(ClientType.ADMIN)) {
			throw new CustomLoginExeption("User-Name - :" + userName + " or Password - " + password + " or Type - " +clientType);
		}

		return new LoggedUser(1234, clientType, AdminServiceImpl.USERNAME, AdminServiceImpl.PASSWORD);
	}

}