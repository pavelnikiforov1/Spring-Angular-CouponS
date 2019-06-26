package Coupon.Progect.CouponProject.Entities;

import java.util.ArrayList;
import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h3>the Customer <b>Entity</b></h3>
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
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	@Size(max = 15)
	@Column(unique = true)
	@NotNull
	@NotBlank
	private String custName;
	@Size(max = 15)
	@NotNull
	private String password;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Collection<Coupon> couponList = new ArrayList<>();

}
