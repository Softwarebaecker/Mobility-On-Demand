/**
 * Based on IIS Script WS2019/20 (by Volker Stiehl)
 */

package edu.thi.mobilityondemand.process.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.thi.mobilityondemand.process.jpa.Customer;

/**
 * Session Bean implementation class CustomerServiceBean
 */
@Stateless
@Named
@LocalBean
public class CustomerServiceBean implements CustomerServiceBeanRemote, CustomerServiceBeanLocal {

	@PersistenceContext
	EntityManager em;

	public Customer create(Customer customer) {
		em.persist(customer);
		return customer;
	}

	public Customer read(Long id) {
		return this.em.find(Customer.class, id);
	}
	
	public String getDicountGroup(Long id) {
		return this.read(id).getDiscountGroup();
	}

	public Customer[] search(String email) {
		List<Customer> customers = null;
		String searchEmail = (email == null || email == "") ? "%" : "%" + email + "%";

		TypedQuery<Customer> query = em.createNamedQuery(Customer.searchCustomer, Customer.class);
		query.setParameter(1, searchEmail);
		customers = query.getResultList();
		Customer[] customerArray = new Customer[customers.size()];
		customerArray = customers.toArray(customerArray);
		return customerArray;
	}

	public void delete(Long id) {
		Customer customer = read(id);
		if (customer != null)
			this.em.remove(customer);
	}

	public void update(Customer customer) {
		this.em.merge(customer);
	}
}
