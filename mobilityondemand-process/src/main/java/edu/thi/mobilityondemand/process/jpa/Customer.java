/**
 * Based on IIS Script WS2019/20 (by Volker Stiehl)
 * Adjustments with project specific customer properties ( @author Daniel Schels ) 
 */

package edu.thi.mobilityondemand.process.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
@NamedQuery(name=Customer.searchCustomer,
	query="SELECT c FROM Customer c WHERE c.email LIKE ?1")
@Table(name="customer")
public class Customer implements Serializable {
    public final static String searchCustomer = "Customer.searchCustomer";
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerid;
    private String firstname;
    private String lastname;
    private String email;
    private Integer yearOfBirth;
    private String adress;
    private String discountGroup;

	public Customer() {
		super();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public String getDiscountGroup() {
		return discountGroup;
	}

	public void setDiscountGroup(String discountGroup) {
		this.discountGroup = discountGroup;
	}
   
	@Override
    public String toString() {
        return "Id: " + customerid + "; Firstname: " + firstname + "; Lastname: " + lastname 
        		+ "; Email: " + email + "; Year of Birth: " + yearOfBirth
        		+ "; Adress: " + adress + "; Discount Group: " + discountGroup;
    }
}
