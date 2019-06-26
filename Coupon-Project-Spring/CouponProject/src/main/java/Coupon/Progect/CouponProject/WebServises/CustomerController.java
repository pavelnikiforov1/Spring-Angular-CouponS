package Coupon.Progect.CouponProject.WebServises;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.Services.CustomerServise;

/**
 * A WebService Class that provides an entry point to the server. path =
 * "/rest/Customer"
 * <h2>data members</h2>
 *
 * - CustomerServise <br>
 * 
 * @author pavel
 *
 */
@RestController
@RequestMapping("/rest/Customer")
public class CustomerController {

	
	@Autowired
	private CustomerServise customerServise;
	/**
	 * A method that verifies that there is a valid session
	 * 
	 * @param session -.
	 * @return LoggedUser
	 */
	private LoggedUser getLoggdUser(HttpSession session) {
		return (LoggedUser) session.getAttribute("LoggedUser");
	}
	
	/**
	 * <h3>Purchase Coupon</h3>
	 * verifies that there is a valid session .<br>
	 * receive a couponId as a @PathVariable and passes it to the customer service . path = "purchaseCoupon/{couponId}".
	 * @param couponId - (int)
	 * @param session -(HttpSession)
	 * @throws CustomDateBaseExepation -.
	 * @throws CustomValidationExepation -.
	 */
	@PostMapping(path = "purchaseCoupon/{couponId}")
	public void purchaseCoupon(@PathVariable int couponId ,HttpSession session) throws  CustomDateBaseExepation, CustomValidationExepation{
		customerServise.purchaseCoupon(couponId, getLoggdUser(session).getId());
		
	}
	
	/**
	 * <h3>Get All Purchased Coupons</h3>path = "getAllPurchasedCoupons".
	 * verifies that there is a valid session .<br>
	 * @param session - (HttpSession)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -.
	 */
	@GetMapping(path = "getAllPurchasedCoupons")
	public List<Coupon> getAllPurchasedCoupons(HttpSession session) throws  CustomDateBaseExepation{
		return customerServise.getAllPurchasedCoupons(getLoggdUser(session).getId());
	}
	/**
	 * <h3>Get All Purchased Coupons By Type</h3>
	 * verifies that there is a valid session .<br>
	 * receive a CouponType as a @PathVariable and passes it to the customer service .
	 * path = "getAllPurchaseCouponsByType/{type}"
	 * @param type - (CouponType)
	 * @param session - (HttpSession)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation - 
	 */
	@GetMapping(path = "getAllPurchaseCouponsByType/{type}")
	public List<Coupon> getAllPurchaseCouponsByType(@PathVariable CouponType type,HttpSession session) throws  CustomDateBaseExepation {
		
		
		return customerServise.getAllPurchaseCouponsByType(type, getLoggdUser(session).getId());
	}
	/**
	 * <h3>Get All Purchased Coupons By Type</h3>
	 * verifies that there is a valid session .<br>
	 * receive a price as a @PathVariable and passes it to the customer service .
	 * path = "getAllPurchesCouponsByPrice/{price}"
	 * @param price - (double)
	 * @param session - (HttpSession)
	 * @return List(Coupon) - 
	 * @throws CustomDateBaseExepation - 
	 */
	@GetMapping(path = "getAllPurchesCouponsByPrice/{price}")
	public List<Coupon> getAllPurchesCouponsByPrice(@PathVariable double price,HttpSession session) throws   CustomDateBaseExepation {
		
		return customerServise.getAllPurchesCouponsByPrice(price, getLoggdUser(session).getId());
	}

	
	
}
