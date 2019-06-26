package Coupon.Progect.CouponProject.WebServises;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.Services.CompanyService;

/**
 * A WebService Class that provides an entry point to the server. path =
 * "/rest/Company"
 * <h2>data members</h2>
 *
 * - CompanyService <br>
 * 
 * @author pavel
 *
 */
@RestController
@RequestMapping("/rest/Company")
public class CompanyController {

	@Autowired
	private CompanyService companyServise;

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
	 * /**
	 * <h3>Create Coupon</h3> verifies that there is a valid session .<br>
	 * receive a Coupon as a @RequestBody and passes it to the admin service . path
	 * = "createCoupon".
	 * 
	 * @param coupon  (Coupon)
	 * @param session (HttpSession)
	 * @return Coupon -
	 * @throws CustomValidationExepation -
	 * @throws CustomDateBaseExepation -
	 */
	@PostMapping(path = "createCoupon")
	public Coupon createCoupon(@RequestBody Coupon coupon, HttpSession session)
			throws CustomValidationExepation, CustomDateBaseExepation {
		System.out.println(coupon);
		companyServise.createCoupon(coupon, getLoggdUser(session).getId());

		return coupon;
	}
	
	/**
	 * /**
	 * <h3>Update Coupon</h3> verifies that there is a valid session .<br>receive a Coupon as a @RequestBody and passes it to
	 * the company service . path = "updateCoupon".
	 * 
	 * @param coupon - (Coupon)
	 * @param session - (HttpSession)
	 * @throws CustomValidationExepation - 
	 * @throws CustomDateBaseExepation - 

	 */
	@PutMapping(path = "updateCoupon")
	void updateCoupon(@RequestBody Coupon coupon, HttpSession session)
			throws CustomValidationExepation, CustomDateBaseExepation {
		
		companyServise.updateCoupon(coupon, getLoggdUser(session).getId());
	}
	
	/**
	 * /**
	 * <h3>Get Coupon</h3> verifies that there is a valid session .<br>receive a coupon id as a @PathVariable and passes it to
	 * the company service . path = "getCoupon/{couponId}".
	 * @param couponId = (int)
	 * @param session = (HttpSession)
	 * @return Coupon
	 * @throws CustomValidationExepation - 
	 * @throws CustomDateBaseExepation - 
	 */
	@GetMapping(path = "getCoupon/{couponId}")
	public Coupon getCoupon(@PathVariable int couponId, HttpSession session)
			throws CustomValidationExepation, CustomDateBaseExepation {
		System.out.println(couponId);

		return companyServise.getCoupon(couponId, getLoggdUser(session).getId());
	}

	
	/**
	 *<h3>Delete Coupon</h3> verifies that there is a valid session .<br>receive a coupon id as a @PathVariable and passes it
	 * to the company service . path = "removeCoupon/{couponId}"
	 * 
	 * @param couponId (int)
	 * @param session (HttpSession)
	 * @throws CustomValidationExepation - 
	 * @throws CustomDateBaseExepation -
	 */
	@DeleteMapping(path = "removeCoupon/{couponId}")
	public void removeCoupon(@PathVariable int couponId, HttpSession session)
			throws CustomValidationExepation, CustomDateBaseExepation {
		companyServise.removeCoupon(couponId, getLoggdUser(session).getId());
	}
	
	/**
	 * * <h3>Get All Coupons</h3> path = "getAllCoupons".
	 * verifies that there is a valid session .<br>
	 * @param session (HttpSession)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -
	 */
	@GetMapping(path = "getAllCoupons")
	public List<Coupon> getAllCoupons(HttpSession session) throws CustomDateBaseExepation {
		return companyServise.getAllCoupons(getLoggdUser(session).getId());

	}
	/**
	 * * <h3>Get All Coupons By Type</h3> path = "getCouponsByType/{type}"
	 * verifies that there is a valid session .<br> passes a type to the service.
	 * @param session (HttpSession)
	 * @param type = (CouponType)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -
	 */
	@GetMapping(path = "getCouponsByType/{type}")
	public List<Coupon> getCouponsByType(@PathVariable CouponType type, HttpSession session)
			throws CustomDateBaseExepation {

		return companyServise.getCouponsByType(type, getLoggdUser(session).getId());

	}
	/**
	 * * <h3>Get All Coupons By price</h3> path = "getCouponsByType/{price}"
	 * verifies that there is a valid session .<br> passes a price to the service.
	 * @param session (HttpSession)
	 * @param price = (double)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -
	 */
	@GetMapping(path = "getCouponsByPrice/{price}")
	public List<Coupon> getCouponsByPrice(@PathVariable double price, HttpSession session)
			throws CustomDateBaseExepation {
		return companyServise.getCouponsByPrice(price, getLoggdUser(session).getId());

	}
	/**
	 * * <h3>Get All Coupons By date</h3> path = "getCouponsByType/{date}"
	 * verifies that there is a valid session .<br> passes a date to the service.
	 * @param session (HttpSession)
	 * @param date = (Date)
	 * @return List(Coupon)
	 * @throws CustomDateBaseExepation -
	 */
	@GetMapping(path = "getCouponsByDate/{date}")
	public List<Coupon> getCouponsByType(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date,
			HttpSession session) throws CustomDateBaseExepation {
		return companyServise.getCouponsByDate(date, getLoggdUser(session).getId());

	}

}
