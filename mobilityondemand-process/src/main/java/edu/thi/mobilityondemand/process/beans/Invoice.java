/*
 * Daniel Schels
 */


package edu.thi.mobilityondemand.process.beans;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;


@LocalBean
@XmlRootElement(name = "Invoice")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String invoiceId;
	private String product;
	private String departure;
	private String destination;
	private Double distance;
	private Double amount;
	private String comment;
	private Date date;
	
	public Invoice() {
		this.product = "Mobility on Demand";
		this.departure = null;
		this.destination = null;
		this.distance = 0.0;
		this.amount = 0.0;
		this.comment = "Thanks for flying with us! See you soon.";
	}
	
	public Invoice(Long tripid, Date date, String departure, String destination, Double distance, Double amount) {
		//this.invoiceId = (int) Math.ceil(Math.random()*10000);
		this.invoiceId = "MOD_" + tripid.toString();
		this.product = "Mobility on Demand";
		this.comment = "Thanks for flying with us! See you soon.";
		this.setDate(date);
		this.departure = departure;
		this.destination = destination;
		this.distance = distance;
		this.amount = amount;
	}
	
	
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
