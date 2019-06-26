package Coupon.Progect.CouponProject.Exeptions;


import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * A CLASS that handles all the Exceptions that are thrown from the Server.
 * and present a user friendly message 
 * @author pavel
 *
 */
@ControllerAdvice
public class CustomExeptionHendler {
	
	
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the Throwable exception at the tree
	 * @param t -.
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( Throwable.class )
	public ResponseEntity<?> handleThrowablel(Throwable t) {
		t.printStackTrace();
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"Something wrong happened... Please contact the admin." + t.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the ValidationException exception at the tree
	 * @param v - ValidationException
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( ValidationException.class )
	public ResponseEntity<?> handleValidation(ValidationException v) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"one or more values you enter was not valide - " + v.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the CustomValidationExepation exception at the tree
	 * @param v - CustomValidationExepation
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( CustomValidationExepation.class )
	public ResponseEntity<?> handleValidation(CustomValidationExepation v) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"opps somthing went wrong  " + v.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.FORBIDDEN);	
	}
	
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the CustomDateBaseExepation exception at the tree
	 * @param d - DateBaseExepation
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( CustomDateBaseExepation.class )
	public ResponseEntity<?> handleValidation(CustomDateBaseExepation d) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"date base validation - " + d.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.CONFLICT);	
	}
	
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the CustomLoginExeption exception at the tree
	 * @param l - CustomLoginExeption 
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( CustomLoginExeption.class )
	public ResponseEntity<?> handleLogin(CustomLoginExeption l) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
				"Password or User Name are INCORRECT . " + l.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.UNAUTHORIZED);	
	}
	/**
	 * ExceptionHandler <b>Method</b> 
	 * Handles all of the exception that are closer to the MethodArgumentTypeMismatchException exception at the tree
	 * @param l - MethodArgumentTypeMismatchException
	 * @return - ResponseEntity(Object) that contains array of messages  :List(String) and status code.
	 * 			
	 */
	@ExceptionHandler( MethodArgumentTypeMismatchException.class )
	public ResponseEntity<?> handleLogin(MethodArgumentTypeMismatchException l) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
				"you have to choose an option " + l.getMessage());
		return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	
}
