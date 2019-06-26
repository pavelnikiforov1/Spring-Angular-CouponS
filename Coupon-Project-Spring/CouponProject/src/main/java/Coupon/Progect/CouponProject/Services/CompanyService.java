package Coupon.Progect.CouponProject.Services;



import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.validation.annotation.Validated;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
/**
 * Interface class that contains all of the relevant COMPANY SERVICE method signature
 * uses the validated Variant to validated all parameters provided.
 * @author pavel
 *
 */
@Validated
public interface CompanyService {
	
	public void createCoupon(@Valid Coupon coupon ,@Positive int id ) throws CustomValidationExepation,  CustomDateBaseExepation;

	void updateCoupon( @Valid Coupon coupon, @Positive int id) throws CustomValidationExepation, CustomDateBaseExepation;

	public Coupon getCoupon(@Positive int couponid ,@Positive int companyid) throws CustomValidationExepation, CustomDateBaseExepation;

	void removeCoupon(@Positive int couponId,@Positive int id) throws CustomValidationExepation, CustomDateBaseExepation;
	
	List<Coupon> getAllCoupons(@Positive int compId) throws CustomDateBaseExepation;

	List<Coupon> getCouponsByType(CouponType type , @Positive int compId) throws CustomDateBaseExepation;
	
	List<Coupon> getCouponsByPrice(@Positive double price , @Positive int compId) throws CustomDateBaseExepation;

	List<Coupon> getCouponsByDate(Date date , @Positive int compId) throws CustomDateBaseExepation;
	
	public LoggedUser login(@Valid String userName,@Valid String password,ClientType clientType) throws CustomValidationExepation, CustomLoginExeption;

	
	
}
