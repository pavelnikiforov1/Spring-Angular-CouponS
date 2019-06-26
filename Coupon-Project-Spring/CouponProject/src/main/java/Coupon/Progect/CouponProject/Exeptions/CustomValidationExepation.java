package Coupon.Progect.CouponProject.Exeptions;


/**
 * A <b>Custom</b> exception class that is used to validate the data that comes to the server 
 * both in the Validation Class and the different Services
 * @author pavel
 *
 */
public class CustomValidationExepation extends CouponSystemExeption {

	public CustomValidationExepation() {
		super();
	}

	public CustomValidationExepation(String message) {
		super(message);
	}

}
