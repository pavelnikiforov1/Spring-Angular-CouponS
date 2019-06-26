package Coupon.Progect.CouponProject.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.ripositories.CouponRipository;
import Coupon.Progect.CouponProject.ripositories.CustomerRipository;

/**
 * Service Class that implements all of the CustomerService methods
 * <h2>data members</h2> -CouponRipository<br>
 * -CompanyRipository<br>
 * 
 * @author pavel
 *
 */
@Service
public class CustomerServiseImpl implements CustomerServise {

	@Autowired
	private CustomerRipository customerRipository;
	@Autowired
	private CouponRipository couponRipository;

	/**
	 * <h3>Purchase Coupon</h3> A method that receives a coupon id and customer id .
	 * creates a coupon instance if there is a coupon with this id in the data base.
	 * creates a customer instance if there is a customer with this id in the data
	 * base. Controls if this customer already have this coupon . Controls if this
	 * coupon has sufficient stock . Adds the customer to the coupons customer list.
	 * Sets the amount of stock left for the coupon. Adds the coupon to the
	 * customers coupon list. Uses the CouponRipository.class to save the coupon.
	 * Uses the CustomerRipository.class to save the customer.
	 * 
	 * @param couponId   - coupon ID (int).
	 * @param customerId - customer ID (int).
	 * 
	 * @throws CustomValidationExepation - in case there is not sufficient amount .
	 * @throws CustomDateBaseExepation   - in case coupon or customer do not exist,
	 *                                   or this customer have this coupon already.
	 * 
	 */
	@Transactional
	@Override
	public void purchaseCoupon(int couponId, int customerId) throws CustomValidationExepation, CustomDateBaseExepation {

		Coupon coupon = couponRipository.findById(couponId).get();
		if (coupon == null) {
			throw new CustomDateBaseExepation("no coupon with this id - " + couponId);
		}
		Customer customer = customerRipository.findById(customerId).get();
		if (customer == null) {
			throw new CustomDateBaseExepation("no customer with this id - " + customerId);
		}
		if (couponRipository.getCustomer(couponId, customerId).size() > 0) {
			throw new CustomDateBaseExepation("you allredy have this coupon ");
		}
		if (coupon.getAmount() <= 0) {
			throw new CustomValidationExepation("this coupon is not avelliballe at the moment - " + coupon.getTitle());
		}

		coupon.getCustomerList().add(customer);
		coupon.setAmount(coupon.getAmount() - 1);
		customer.getCouponList().add(coupon);
		couponRipository.save(coupon);
		customerRipository.save(customer);

	}

	/**
	 * <h3>Get All Purchased Coupons</h3> A method that uses
	 * customerRipository.Class to find all coupons that belong to a specific
	 * customer in the data base .
	 * 
	 * @param id - customer ID (int)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this
	 *                                 customer at the data base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getAllPurchasedCoupons(int id) throws CustomDateBaseExepation {

		if (customerRipository.findById(id) == null) {
			throw new CustomDateBaseExepation("no customer with this id - " + id);
		}

		List<Coupon> c = customerRipository.getAllPurchasedCoupons(id);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no purchased coupons for this customer ");
		}

		return c;
	}

	/**
	 * <h3>Get All Purchased Coupons By Type</h3> A method that uses
	 * customerRipository.Class to find all coupons that belong to a specific
	 * customer and are from the provided type in the data base .
	 * 
	 * @param id   - customer ID (int)
	 * @param type - Coupon Type (CouponType)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this
	 *                                 customer at the data base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getAllPurchaseCouponsByType(CouponType type, int id) throws CustomDateBaseExepation {

		List<Coupon> c = customerRipository.getAllCouponsByType(id, type);
		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons in this categorie for this customer ");
		}

		return c;
	}

	/**
	 * <h3>Get All Purchased Coupons By Price</h3> A method that uses
	 * customerRipository.Class to find all coupons that belong to a specific
	 * customer and cost less then the price provided in the data base .
	 * 
	 * @param id    - customer ID (int)
	 * @param price - price (double)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this
	 *                                 customer at the data base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getAllPurchesCouponsByPrice(double price, int id) throws CustomDateBaseExepation {

		List<Coupon> c = customerRipository.getAllCouponsByPrice(id, price);
		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons with a price lower then this for this customer ");
		}

		return c;
	}

	/**
	 * <h2>Login</h2> this method uses the parameters provided and performs a
	 * comparison to the data base .
	 * 
	 * @param userName   - the user name of the client (String)
	 * @param password   - the password of the client (String)
	 * @param clientType - the client Type of the client (ClientType)
	 * 
	 * @throws CustomLoginExeption -      in case one of the parameters is not
	 *                                   correct
	 * @throws CustomValidationExepation - in case type is incorrect
	 * @return new LoggedUser with users details
	 */
	@Override
	public LoggedUser login(String userName, String password, ClientType clientType)
			throws CustomValidationExepation, CustomLoginExeption {

		if (clientType != ClientType.CUSTOMER) {
			throw new CustomValidationExepation("not a valid type");
		}
		Optional<Customer> c = customerRipository.login(userName, password);

		if (!c.isPresent()) {
			throw new CustomLoginExeption("User-Name - : " + userName + " or Password - " + password + " or Type -  " +clientType);
		}

		return new LoggedUser(c.get().getId(),clientType,userName,password);
	}

}
