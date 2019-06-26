package Coupon.Progect.CouponProject.Exeptions;


/**
 * Exception class which will be the root class for all of the <b>Custom</b> exceptions in the program 
 * @author pavel
 *
 */
public class CouponSystemExeption extends Exception {
	
	public CouponSystemExeption() {
		super();
	}

	public CouponSystemExeption(String message) {
		super(message);
	}
}
