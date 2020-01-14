package edu.thi.mobilityondemand.servicetask;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;


import edu.thi.mobilityondemand.process.beans.CustomerServiceBean;
import edu.thi.mobilityondemand.process.beans.CustomerServiceBeanLocal;
import edu.thi.mobilityondemand.process.jpa.Customer;

@Stateless
@LocalBean
@Named
public class InitialCustomerDBSetup implements CustomerServiceBeanLocal{
    @Inject
    CustomerServiceBean customerServiceBean;

    public void createCustomer() {
        Customer customer = new Customer();
        customer.setDiscountGroup("student");
        customer.setEmail("ror123@thi.de");
        customer.setFirstname("Robert");
        customer.setLastname("Rohrer");
        customer.setYearOfBirth(19930125);
        customer.setAdress("Esplanade 10, 85049 Ingolstadt");
    	Customer c = customerServiceBean.create(customer);
    	
    	System.out.println(c);
    }
}
