package Coupon.Progect.CouponProject.ripositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Coupon.Progect.CouponProject.Entities.Company;
import Coupon.Progect.CouponProject.Entities.Coupon;
import Coupon.Progect.CouponProject.Entities.CouponType;
import Coupon.Progect.CouponProject.Entities.Customer;

/**
 * <h3>CustomerRipository</h3> A Interface class that extends JpaRepository main
 * interface.
 * 
 * @author pavel
 *
 */
@Repository
public interface CustomerRipository extends JpaRepository<Customer, Integer> {

	/**
	 * Custom JPQL method that updates a customer with given parameters by the
	 * customer id
	 * 
	 * @param password - new price
	 * @param id       - id of the customer
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Customer c SET c.password = :password where c.id = :id")
	public void updateCustomer(@Param("password") String password, int id);

	/**
	 * Custom JPQL method that finds all purchased coupons of a customer by provided
	 * id
	 * 
	 * @param customerId - customer ID
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.customerList cc where cc.id = :customerId")
	public List<Coupon> getAllPurchasedCoupons(@Param("customerId") int customerId);

	/**
	 * Custom JPQL method that finds all purchased coupons that has a specific type
	 * of a customer by provided id
	 * 
	 * @param customerId - customer ID
	 * @param couponType - coupon type
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.customerList cc where cc.id = :customerId and c.type = :couponType")
	public List<Coupon> getAllCouponsByType(@Param("customerId") int customerId,
			@Param("couponType") CouponType couponType);

	/**
	 * Custom JPQL method that finds all purchased coupons that has a price lower
	 * than the one provided of a customer by provided id
	 * 
	 * @param customerId - customer ID
	 * @param price      - price of a coupon
	 * @return List(Coupon)
	 */
	@Query("select c from Coupon c JOIN c.customerList cc where cc.id = :customerId and c.price < :price")
	public List<Coupon> getAllCouponsByPrice(@Param("customerId") int customerId, @Param("price") double price);


	/**
	 * Custom  method that finds if there is a customer with this id at the date base
	 * @param id - customer id 
	 * @return Optional(Customer) 
	 */
	public Optional<Customer> findById(int id);
	
	
	/**
	 * Custom JPQL method that verifies the the user name and password against the
	 * data base
	 * 
	 * @param userName - user name
	 * @param password - password
	 * @return Optional(Customer)
	 */
	@Query("select c from Customer c where c.custName = :name and c.password = :password")
	public Optional<Customer> login(@Param("name") String userName, @Param("password") String password);

}
