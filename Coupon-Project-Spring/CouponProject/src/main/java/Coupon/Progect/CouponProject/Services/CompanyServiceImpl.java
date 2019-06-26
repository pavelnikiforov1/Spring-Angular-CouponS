package Coupon.Progect.CouponProject.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import Coupon.Progect.CouponProject.Entities.ClientType;
import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.Customer;
import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.ripositories.CompanyRipository;
import Coupon.Progect.CouponProject.ripositories.CouponRipository;
import Coupon.Progect.CouponProject.validations.Validations;

/**
 * Service Class that implements all of the CompanyService methods
 * <h2>data members</h2> -CouponRipository<br>
 * -CompanyRipository<br>
 * -Validations
 * 
 * @author pavel
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CouponRipository couponRipository;
	@Autowired
	private CompanyRipository companyRipository;
	@Autowired
	private Validations validations;

	/**
	 * <h3>Create Coupon</h3> A method that receives a coupon validates it by the
	 * Validation.Class sets its company and uses the CouponRipository.Class to save
	 * it in the date base .
	 * 
	 * @param coupon - coupon Entity (coupon)
	 * @param id     - company ID (int)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid.
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base.
	 */
	@Override
	public void createCoupon(Coupon coupon, int id) throws CustomValidationExepation, CustomDateBaseExepation {

		String create = "create";

		validations.validateCoupon(coupon, id, create);

		Optional<Company> c = companyRipository.findById(id);

		coupon.setCompany(c.get());

		couponRipository.save(coupon);

	}

	/**
	 * <h3>Update Coupon</h3> A method that receives a coupon validates it by the
	 * Validation.Class and uses the CouponRipository.Class to update it .
	 * 
	 * @param coupon - Coupon Entity (Coupon)
	 * @param id     - company id (int)
	 * @throws CustomValidationExepation - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters is
	 *                                   not authorized by the date base
	 */
	@Override
	public void updateCoupon(Coupon coupon, int id) throws CustomValidationExepation, CustomDateBaseExepation {

		String ownership = "ownership";

		validations.validateCoupon(coupon, id, ownership);

		couponRipository.updateCoupon(coupon.getPrice(), coupon.getEndDate(), coupon.getId());
	}

	/**
	 * <h3>Delete Coupon</h3> A method that receives a ID of a coupon to be deleted
	 * and id of the company its belong to. validate it by Validation.Class and uses
	 * the CouponRipository.Class to delete it .
	 * 
	 * @param couponId  - coupon ID (int)
	 * @param companyid - company ID (int)
	 */
	@Transactional
	@Modifying
	@Override
	public void removeCoupon(int couponId, int companyid) throws CustomValidationExepation, CustomDateBaseExepation {
		String ownership = "ownership";
		
		System.out.println(couponId + "  " + companyid);
		
		Optional<Coupon> c = couponRipository.findById(couponId);
		
		validations.validateCoupon(c.get(), companyid, ownership);

		couponRipository.removeCouponsFromCompany(couponId);
	
	}

	/**
	 * <h3>Get Coupon</h3> A method that receives a coupon id and a company id
	 * validate it by Validation.Class and uses the CouponRipository.Class to get it
	 * 
	 * @param couponId  - coupon ID (int)
	 * @param companyid - company ID (int)
	 * @throws CustomDateBaseExepation   - in case one or more of the parameters
	 *                                   provided are not valid
	 * @throws CustomValidationExepation -in case one or more of the parameters is
	 *                                   not authorized by the date base
	 * @return Coupon - c
	 */
	@Override
	public Coupon getCoupon(int couponId, int companyid) throws CustomValidationExepation, CustomDateBaseExepation {

		String ownership = "ownership";
		Coupon c = couponRipository.findById(couponId).get();
		validations.validateCoupon(c, companyid, ownership);

		return c;
	}

	/**
	 * <h3>Get All Coupons</h3> A method that uses CouponRipository.Class to find
	 * all coupons that belong to a specific company in the data base .
	 * 
	 * @param compId - company ID (int)
	 * @throws CustomDateBaseExepation - in case there is no coupons for this
	 *                                 company at the data base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getAllCoupons(int compId) throws CustomDateBaseExepation {

		List<Coupon> c = couponRipository.findAllById(compId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons for this company ");
		}

		return c;
	}

	/**
	 * <h3>Get All Coupons By Type</h3> A method that uses CouponRipository.Class to
	 * find all coupons that belong to a specific company and have a type in the
	 * data base .
	 * 
	 * @param compId - company ID (int)
	 * @param type   - coupon type (CouponType)
	 * @throws CustomDateBaseExepation - in case there is no coupons with this type
	 *                                 for this company at the data base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getCouponsByType(CouponType type, int compId) throws CustomDateBaseExepation {

		List<Coupon> c = couponRipository.findAllBytype(type, compId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons with this type" + type);
		}
		return c;
	}

	/**
	 * <h3>Get All Coupons By Price</h3> A method that uses CouponRipository.Class
	 * to find all coupons that belong to a specific company and have a Price lower
	 * than the one provided in the data base .
	 * 
	 * @param compId - company ID (int)
	 * @param price  - coupon price (double)
	 * @throws CustomDateBaseExepation - in case there is no coupons with a price
	 *                                 lower then this for this company at the data
	 *                                 base.
	 * @return List(Coupon)
	 */
	@Override
	public List<Coupon> getCouponsByPrice(@Positive double price, @Positive int compId) throws CustomDateBaseExepation {
		List<Coupon> c = couponRipository.findAllByPrice(price, compId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons with this price lower then this - " + price);
		}
		return c;
	}

	/**
	 * <h3>Get All Coupons By Date</h3> A method that uses CouponRipository.Class to
	 * find all coupons that belong to a specific company and have a endDate prior
	 * than the one provided in the data base .
	 * 
	 * @param compId - company ID (int)
	 * @param date   - coupon endDate (Date)
	 * @throws CustomDateBaseExepation - in case there is no coupons with a a
	 *                                 endDate prior than this for this company at
	 *                                 the data base.
	 * @return  c - List(Coupon)
	 */
	@Override
	public List<Coupon> getCouponsByDate(Date date, int compId) throws CustomDateBaseExepation {

		List<Coupon> c = couponRipository.findAllByDate(date, compId);

		if (c.isEmpty()) {
			throw new CustomDateBaseExepation("no coupons with an experation Date sooner then this - " + date);
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
	 *                                   correct .
	 * 
	 * @throws CustomValidationExepation -.
	 * @return new LoggedUser with users details -.
	 * 
	 */
	@Override
	public LoggedUser login(String userName, String password, ClientType clientType)
			throws CustomLoginExeption, CustomValidationExepation {

		if (clientType != ClientType.COMPANY) {
			throw new CustomValidationExepation("not a valid type");
		}

		Optional<Company> c = companyRipository.login(userName, password);

		if (!c.isPresent()) {
			throw new CustomLoginExeption("User-Name - : " + userName + " or Password - " + password + " or Type - " +clientType);
		}

		return new LoggedUser(c.get().getId(),clientType,userName,password);
	}
}
