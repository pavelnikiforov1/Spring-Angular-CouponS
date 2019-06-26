package Coupon.Progect.CouponProject.WebServises;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Coupon.Progect.CouponProject.Entities.LoggedUser;
import Coupon.Progect.CouponProject.Exeptions.CustomDateBaseExepation;
import Coupon.Progect.CouponProject.Exeptions.CustomLoginExeption;
import Coupon.Progect.CouponProject.Exeptions.CustomValidationExepation;
import Coupon.Progect.CouponProject.Services.AdminService;
import Coupon.Progect.CouponProject.Services.CompanyService;
import Coupon.Progect.CouponProject.Services.CustomerServise;


/**
 * A WebService Class that provides an entry point to the server
 * all author Web Services pass via this one . path =".login"
 * <h2>data members</h2>
 * - AdminService <br>
 * - CustomerServise <br>
 * - CompanyService 
 * @author pavel
 *
 */
@RestController
@RequestMapping("login")
public class LogginController {

	@Autowired
	private AdminService adminServise;
	@Autowired
	private CompanyService companyServise;
	@Autowired
	private CustomerServise customerServise;

	
	/**
	 * <h2>Login</h2>
	 * this method receives a Logged User instance as a @RequestBody and passes it to each of the servers accordingly.
	 * in case the login is successful it will set an attribute on the session using the "LoggedUser" name and the user data as a value.
	 * 
	 * @param loggedUser - (LoggedUser)
	 * @param request - (HttpSession) 
	 * @return ResponseEntity(LoggedUser)(lu ,HttpStatus.OK )
	 * @throws CustomValidationExepation - 
	 * @throws CustomDateBaseExepation - 
	 * @throws CustomLoginExeption - 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LoggedUser loggedUser ,HttpSession request)
			throws CustomValidationExepation, CustomDateBaseExepation, CustomLoginExeption {
		
		LoggedUser lu = null ;
		
		switch (loggedUser.getClientType()) {
		case ADMIN:
			lu = adminServise.login(loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType());
			request.setAttribute("LoggedUser", lu);
			break;
		case COMPANY:
			
		  lu =	companyServise.login(loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType());
		  System.out.println(lu);
		  request.setAttribute("LoggedUser", lu);
		  break;
			
		case CUSTOMER:
			lu =customerServise.login(loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType());
			request.setAttribute("LoggedUser", lu);
			System.out.println(lu);
			break;
	}
		
		return new ResponseEntity<LoggedUser>(lu ,HttpStatus.OK );
		
}
	
	/**
	 * <h2>Log Out</h2>path = "/logOut"
	 * invalidate the session and log out from the server.
	 * @param session - .
	 */
	@PostMapping(path = "/logOut")
	public void logOut(HttpSession session) {
		session.invalidate();
	}
}