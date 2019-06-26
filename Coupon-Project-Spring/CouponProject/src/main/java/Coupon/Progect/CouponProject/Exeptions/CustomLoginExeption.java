package Coupon.Progect.CouponProject.Exeptions;



/**
 * A <b>Custom</b> exception class that is used in the Logging methods
 * @author pavel
 *
 */
public class CustomLoginExeption extends CouponSystemExeption{
	
	public CustomLoginExeption() {
		super();
	}

	public CustomLoginExeption(String message) {
		super(message);
	}
}
