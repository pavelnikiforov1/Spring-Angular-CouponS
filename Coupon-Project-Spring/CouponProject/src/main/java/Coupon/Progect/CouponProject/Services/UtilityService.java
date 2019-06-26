package Coupon.Progect.CouponProject.Services;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
/**
 * Interface class that contains all of the relevant Utility SERVICE method signature
 * uses the validated Variant to validated all parameters provided.
 * @author pavel
 *
 */
@Validated
public interface UtilityService {
	
	public List<Coupon> getAllCouponsWhereTypeIs(@NotNull String s);

}
