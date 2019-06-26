package Coupon.Progect.CouponProject.Entities;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h3>LoggedUser</h3>
 * A class that represent the current logged user 
 * Contains all the data members and default methods
 * @author pavel
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggedUser {

	private int id;
	private ClientType clientType;
	private String name;
	private String password;
	

	
	
}
