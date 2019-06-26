package Coupon.Progect.CouponProject.WebServises;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.Services.AdminService;

/**
 * A WebService Class that provides an entry point to the server. path =
 * "/rest/Admin"
 * <h2>data members</h2>
 *
 * - AdminService <br>
 * 
 * @author pavel
 *
 */
@RestController
@RequestMapping("/rest/Admin")
public class AdminController {

	@Autowired
	private AdminService adminServiseImpl;

	/**
	 * <h3>Create Company</h3> receive a Company as a @RequestBody and passes it to
	 * the admin service . path = "createCompany"
	 * 
	 * @param company - (Company)
	 * @return Company.
	 * @throws CustomValidationExepation - 
	 * @throws CustomDateBaseExepation - 
	 */
	@PostMapping(path = "createCompany")
	public Company createCompany(@RequestBody Company company)
			throws CustomValidationExepation, CustomDateBaseExepation {
		adminServiseImpl.createCompany(company);

		return company;

	}

	/**
	 * <h3>Delete Company</h3> receive a company id as a @PathVariable and passes it
	 * to the admin service . path = "deleteCompany/{companyId}"
	 * 
	 * @param companyId - (int)
	 */
	@DeleteMapping(path = "deleteCompany/{companyId}")
	public void deleteCompany(@PathVariable int companyId) {

		adminServiseImpl.deleteCompany(companyId);

	}

	/**
	 * <h3>Update Company</h3> receive a Company as a @RequestBody and passes it to
	 * the admin service . path = "updateCompany"
	 * 
	 * @param company - (Company)
	 * @throws CustomValidationExepation -.
	 * @throws CustomDateBaseExepation -.
	 */
	@PutMapping(path = "updateCompany")
	public void updateCompany(@RequestBody Company company) throws CustomValidationExepation, CustomDateBaseExepation {
		adminServiseImpl.updateCompany(company);

	}

	/**
	 * <h3>Get Company</h3> receive a company id as a @PathVariable and passes it to
	 * the admin service . path = "getCompany/{companyId}".
	 * 
	 * @param companyId - (int)
	 * @return Company -.
	 * @throws CustomDateBaseExepation -.
	 */
	@GetMapping(path = "getCompany/{companyId}")
	public Company getCompany(@PathVariable int companyId) throws CustomDateBaseExepation {

		return adminServiseImpl.getCompany(companyId);
	}

	/**
	 * <h3>Get All Companies</h3> path = "getAllCompanys"
	 * 
	 * @return List(Company)
	 * @throws CustomDateBaseExepation -.
	 */
	@GetMapping(path = "getAllCompanys")
	public List<Company> getAllCompanys() throws CustomDateBaseExepation {

		return adminServiseImpl.getAllCompanies();
	}

	/**
	 * <h3>Get All Company Coupons</h3> receive a company id as a @PathVariable and
	 * passes it to the admin service . path = "getAllCompanyCoupons/{companyId}".
	 * 
	 * @param companyId (int)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -.
	 */
	@GetMapping(path = "getAllCompanyCoupons/{companyId}")
	public List<Coupon> getAllCompanyCoupons(@PathVariable int companyId) throws CustomDateBaseExepation {

		return adminServiseImpl.getAllCompanyCoupons(companyId);
	}

	/**
	 * <h3>Create Customer</h3> receive a Customer as a @RequestBody and passes it
	 * to the admin service . path = "createCustomer"
	 * 
	 * @param customer - (Customer)
	 * @return Customer.
	 * @throws CustomValidationExepation -.
	 * @throws CustomDateBaseExepation -.
	 */
	@PostMapping(path = "createCustomer")
	public Customer createCustomer(@RequestBody Customer customer)
			throws CustomValidationExepation, CustomDateBaseExepation {
		System.out.println(customer);
		adminServiseImpl.createCustomer(customer);

		return customer;
	}

	/**
	 * <h3>Remove Customer</h3> receive a customer id as a @PathVariable and passes
	 * it to the admin service . path = "removeCustomer/{customerId}".
	 * 
	 * @param customerId - (int)
	 */
	@DeleteMapping(path = "removeCustomer/{customerId}")
	public void removeCustomer(@PathVariable int customerId) {

		adminServiseImpl.removeCustomer(customerId);
	}

	/**
	 * <h3>Update Customer</h3> receive a customer as a @RequestBody and passes it
	 * to the admin service . path = "updateCustomer".
	 * 
	 * @param customer - (Customer)
	 * @throws CustomValidationExepation -.
	 * @throws CustomDateBaseExepation -.
	 */
	@PutMapping(path = "updateCustomer")
	public void updateCustomer(@RequestBody Customer customer)
			throws CustomDateBaseExepation, CustomValidationExepation {

		adminServiseImpl.updateCustomer(customer);
	}

	/**
	 * <h3>Get Customer</h3> receive a customer id as a @PathVariable and passes it
	 * to the admin service .path = "getCustomer/{customerId}".
	 * 
	 * @param customerId  - (int)
	 * @return Customer
	 * @throws CustomValidationExepation - . 
	 * @throws CustomDateBaseExepation - . 
	 */
	@GetMapping(path = "getCustomer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId)
			throws CustomValidationExepation, CustomDateBaseExepation {
		return adminServiseImpl.getCustomer(customerId);
	}

	/**
	 * <h3>Get All Customers</h3> path = "getAllCustomers".
	 * 
	 * @return List(Company)
	 * @throws CustomDateBaseExepation - . 
	 */
	@GetMapping(path = "getAllCustomers")
	public List<Customer> getAllCustomer() throws  CustomDateBaseExepation {

		return adminServiseImpl.getAllCustomer();

	}

	/**
	 * <h3>Get All Customer Coupons</h3> receive a customer id as a @PathVariable
	 * and passes it to the admin service . path = "getAllCustomerCoupons/{custId}".
	 * 
	 * @param custId (int)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -
	 */
	@GetMapping(path = "getAllCustomerCoupons/{custId}")
	public List<Coupon> getAllCustomerCoupons(@PathVariable int custId) throws CustomDateBaseExepation {

		return adminServiseImpl.getAllCustomerCoupons(custId);
	}
}
