package Coupon.Progect.CouponProject.Services;


import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;

/**
 * Interface class that contains all of the relevant CUSTOMER SERVICE method signature
 * uses the validated Variant to validated all parameters provided.
 * @author pavel
 *
 */
@Validated
public interface CustomerServise {

	public void purchaseCoupon(@Positive int couponId , @Positive int customerId) throws CustomValidationExepation,CustomDateBaseExepation;

	public List<Coupon> getAllPurchasedCoupons(@Positive int id) throws CustomDateBaseExepation;

	public List<Coupon> getAllPurchaseCouponsByType(CouponType type,@Positive int id) throws CustomDateBaseExepation;

	public List<Coupon> getAllPurchesCouponsByPrice(@Positive double price,@Positive int id) throws CustomDateBaseExepation;

	public LoggedUser login(@Valid String userName, @Valid String password, ClientType clientType) throws CustomValidationExepation, CustomLoginExeption;
}
