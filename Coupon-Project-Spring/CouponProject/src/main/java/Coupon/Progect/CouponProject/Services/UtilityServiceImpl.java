package Coupon.Progect.CouponProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.ripositories.CouponRipository;
import Coupon.Progect.CouponProject.ripositories.CustomerRipository;
import Coupon.Progect.CouponProject.validations.Validations;

/**
 * Service Class that implements all of the UtilityServise methods
 * <h2>data members</h2> - CouponRipository<br>
 * 
 * @author pavel
 *
 */
@Service
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	private CouponRipository couponRipository;

	

	/**
	 * <h3>getAllCouponsWhereTypeIs</h3> method get a string that contains either a
	 * location , a location and type or location and type and id . . in case the location = "main" it will
	 * return a list of coupons with the type provided with max size of 8. in case
	 * the location = "category" it will return a list of all coupons with the type
	 * provided.
	 * 
	 * @param location - (String)
	 * @return coupons - List(Coupon)
	 */
	@Override
	public List<Coupon> getAllCouponsWhereTypeIs(String location) {

		List<Coupon> coupons = new ArrayList<>();

		if (location.split(" ").length == 1) {
			getForMain(location, coupons);
		} else if (location.split(" ").length == 2) {
			inCategory(location, coupons);
		} 
		return coupons;
	}

	
	

	/**
	 * In case the location above is category
	 * @param location - Category
	 * @param coupons - coupon list
	 */
	private void inCategory(String location, List<Coupon> coupons) {
		List<Coupon> findAllBytype = this.couponRipository.findAllBytype(CouponType.valueOf(location.split(" ")[0]));
		int size = findAllBytype.size();

		for (int j = 0; j <= size - 1; j++) {
			coupons.add(findAllBytype.get(j));
		}
	}
	
	/**
	 * in case the location above is main 
	 * @param location - main 
	 * @param coupons - coupon list. 
	 */
	private void getForMain(String location, List<Coupon> coupons) {
		List<Coupon> findAllBytype = this.couponRipository
				.findAllBytype(CouponType.valueOf(location));
		int size = findAllBytype.size();
		if (size >= 8) {
			for (int j = 0; j <= 7; j++) {
				coupons.add(findAllBytype.get(j));
			}
		} else {
			for (int i = 0; i <= size - 1; i++) {
				coupons.add(findAllBytype.get(i));
			}
		}
	}

	
	
}
