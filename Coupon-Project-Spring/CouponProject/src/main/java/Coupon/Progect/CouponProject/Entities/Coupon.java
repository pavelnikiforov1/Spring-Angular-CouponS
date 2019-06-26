package Coupon.Progect.CouponProject.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.Empty;

/**
 * <h3>the Coupon <b>Entity</b></h3>
 * Contains all of the company data members and default methods
 * @author pavel
 *
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Coupon {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true )
	@NotNull
	@NotBlank
	@Size(max=25)
	private String title;
	
	@NotNull
	@Size(max=1000)
	@NotBlank
	private String messege;
	
	@NotNull
	@Size(max=8_000)
	private String image;
	
	@Positive
	@NotNull
	private int amount;

	@Positive
	@NotNull
	private double price;
	

	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@NotNull
	private CouponType type;
	
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "couponList")
	@JsonIgnore
	private List<Customer> customerList = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private Company company;

}