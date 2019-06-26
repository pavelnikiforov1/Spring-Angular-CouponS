package Coupon.Progect.CouponProject;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Coupon.Progect.CouponProject.ripositories.CouponRipository;



/**
 * CouponExpirationDailyJob - runs a thread that deletes all coupons that are expired.
 * <b>runs every 24 H </b>
 * @author pavel
 *
 */
@Component
public class CouponExpirationDailyJob {

	private boolean running;
	private Thread thread;
	
	@Autowired
	private CouponRipository couponRipository;
	
	@PostConstruct
	public synchronized void start() {
	if(running) {
		return;
	}
	running = true;
	this.thread = new Thread(new Runnable() {	
		@Override
		public void run() {		
			couponRipository.removeExpCoupons(new Date(System.currentTimeMillis()));	
			try {
				thread.sleep(60*60*24*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	}

	
		
		
		
		
		


}
