package Coupon.Progect.CouponProject.ripositories;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.Customer;

/**
 * <h3>CouponRipository</h3> A Interface class that extends JpaRepository main
 * interface.
 * 
 * @author pavel
 *
 */
@Repository
public interface CouponRipository extends JpaRepository<Coupon, Integer> {

	/**
	 * Custom JPQL method that finds a coupon of a company in the date base by his
	 * both id's
	 * 
	 * @param compId   - company ID
	 * @param couponId - coupon ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c join c.company cc where cc.id = :companyId and c.id = :couponId")
	public List<Coupon> findByCompanyId(@Param("companyId") int compId, @Param("couponId") int couponId);

	/**
	 * Custom JPQL method that finds all coupons of a company in the date base by
	 * his id
	 * 
	 * @param compId - company ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c Join c.company cc where cc.id = :id")
	public List<Coupon> findAllById(@Param("id") int compId);

	/**
	 * 
	 * Custom JPQL method that finds all coupons of a company in the date base by
	 * his type and company ID
	 * 
	 * @param couponType - the type of the coupons desired
	 * @param companyId  - company ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.company cc where c.type = :couponType and cc.id = :companyId")
	public List<Coupon> findAllBytype(@Param("couponType") CouponType couponType, @Param("companyId") int companyId);

	/**
	 * Custom JPQL method that finds all coupons base by type
	 * 
	 * @param couponType - coupon type
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c where c.type = :couponType")
	public List<Coupon> findAllBytype(@Param("couponType") CouponType couponType);

	/**
	 * Custom JPQL method that updates a coupon with given parameters by the coupon
	 * id
	 * 
	 * @param price   - new price
	 * @param endDate - new endDate
	 * @param id      - id of the coupon
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Coupon c SET c.price= :price , c.endDate = :endDate where c.id = :id")
	public void updateCoupon(@Param("price") Double price, @Param("endDate") Date endDate, @Param("id") int id);

	/**
	 * Custom JPQL method that finds if a coupon is purchased by a customer
	 * 
	 * @param couponId   - coupon ID
	 * @param customerId - customer ID
	 * @return List(Customer)
	 */
	@Query("select c from Customer c join c.couponList cc where c.id = :customerId and cc.id = :couponId  ")
	public List<Customer> getCustomer(@Param("couponId") int couponId, @Param("customerId") int customerId);

	/**
	 * Custom JPQL method that deletes all the coupons that has a been expired
	 * 
	 * @param d - current date
	 */
	@Query("delete from Coupon c where c.endDate = :date")
	public void removeExpCoupons(@Param("date") Date d);

	/**
	 * Custom JPQL method that deletes a company based on id
	 * 
	 * @param id - company ID
	 */
	@Transactional
	@Modifying
	@Query("delete from Coupon c where c.company.id = :id")
	public void removeCoupons(@Param("id") int id);

	/**
	 * Custom JPQL method that deletes all of a companies coupons based on id
	 * 
	 * @param couponId - company ID
	 */
	@Transactional
	@Modifying
	@Query("delete from Coupon c where c.id = :couponId ")
	public void removeCouponsFromCompany(@Param("couponId") int couponId);

	/**
	 * Custom JPQL method that deletes all of a customers coupons based on id
	 * 
	 * @param id - customer ID
	 * @return list(Coupon) 
	 */
	@Query("select c from Coupon c join c.customerList cc where c.id =: id")
	public List<Coupon> removeCouponsFromCustomer(@Param("id") int id);

	/**
	 * Custom JPQL method that finds all coupons that belongs to a company that have
	 * a price lower then the one provided
	 * 
	 * @param price  - price of a coupon
	 * @param compId - company ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.company cc where c.price < :price and cc.id = :companyId")
	public List<Coupon> findAllByPrice(@Param("price") double price, @Param("companyId") @Positive int compId);

	/**
	 * Custom JPQL method that finds all coupons that belongs to a company that have
	 * a expiration date prior then the one provided
	 * 
	 * @param date   - expiration date
	 * @param compId - company ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.company cc where c.endDate < :date and cc.id = :companyId")
	public List<Coupon> findAllByDate(@Param("date") Date date, @Param("companyId") int compId);

	/**
	 * Custom JPQL method that finds all coupons base by the title 
	 * @param title - coupon title
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c where c.title = :title")
	public List<Coupon> findByTitle(@Param("title") String title);

}
