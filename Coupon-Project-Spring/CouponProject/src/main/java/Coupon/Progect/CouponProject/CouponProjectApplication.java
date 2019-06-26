package Coupon.Progect.CouponProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CouponProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponProjectApplication.class, args);

	}
}
