package Coupon.Progect.CouponProject.WebServises;


import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.Services.UtilityService;


/**
 * A WebService Class that provides an entry point to the Utility Server. path = "main".
 * <h2>data members</h2>
 *
 * - UtilityService <br>
 * 
 * @author pavel
 *
 */
@RestController
@RequestMapping("main")
public class UtilityControler {

	@Autowired
	private UtilityService utilityServiseImpl;
	
	/**<h3>Get All Coupons Where Type Is </h3>
	 * this method receives a location as a @PathVariable and pass it to the utility service.
	 *
	 * path = "getAllCouponsWhereTypeIs/{s}"
	 * @param location - string
	 * @return List(Coupon)
	 * @throws CustomValidationExepation - 
	 */
	@GetMapping(path = "getAllCouponsWhereTypeIs/{location}" )
	public List<Coupon> getAllCouponsWhereTypeIs(@PathVariable String location) throws CustomValidationExepation {

		return utilityServiseImpl.getAllCouponsWhereTypeIs(location);
	}
	

	/**
	 * a method for the connection test .
	 */
	@GetMapping(path = "checkCon" )
	public void checkCon(){
		
	}



	
	
	
	
}
