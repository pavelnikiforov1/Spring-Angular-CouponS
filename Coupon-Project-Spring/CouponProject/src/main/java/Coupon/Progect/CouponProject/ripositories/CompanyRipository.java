package Coupon.Progect.CouponProject.ripositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Coupon.Progect.CouponProject.Entities.Company;


/**
 * <h3>CompanyRipository</h3>
 * A Interface class that extends JpaRepository main interface.
 * @author pavel
 *
 */
@Repository
public interface CompanyRipository extends JpaRepository<Company, Integer>{
	
	
	/**
	 * Custom JPQL method that delete a company from the dataBase by Id
	 * @param id - of company to be deleted 
	 */
	@Modifying
	@Query("delete from Company c where c.id = :id")
	public void deleteById(@Param("id")int id);
	/**
	 * Custom JPQL method that updates a company using provided parameters
	 * @param password - password
	 * @param email - email 
	 * @param id - id 
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Company c SET c.password = :password , c.email = :email where c.id = :id")
	public void updateCompany(@Param("password") String password,@Param("email")String email,@Param("id") int id);
	
	/**
	 * Custom  method that finds if there is a company with this id at the date base
	 * @param id - company id
	 * @return Optional(Company) 
	 */
	public  Optional<Company> findById(int id);
	
	
	/**
	 * Custom JPQL method that verifies the the user name and password against the data base 
	 * @param userName - user name 
	 * @param password - password
	 * @return Optional(Company) 
	 */
	@Query("select c from Company c where c.name = :name AND c.password = :password")
	public Optional<Company> login(@Param("name")String userName, @Param("password")String password);
	
}
