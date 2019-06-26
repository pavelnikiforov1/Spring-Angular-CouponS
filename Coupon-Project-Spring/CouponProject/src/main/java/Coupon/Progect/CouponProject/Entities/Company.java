package Coupon.Progect.CouponProject.Entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * <h3>the Company <b>Entity</b></h3>
 * Contains all of the company data members and default methods
 * @author pavel
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Company {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	@Size(max=25)
	@NotNull
	@NotBlank
	private String name;
	
	@Size(max=25)
	@NotNull
	private String password;
	
	
	@Size(max=50)
	@NotNull
	@Email
	private String email ;


	
	
	
	
}
