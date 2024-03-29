/**
 * @author Daniel Schels
 */

package edu.thi.mobilityondemand.process.message;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Invoice")
public class InvoiceMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String product;
    private String departure;
    private String destination;
    private Double distanceKilometres;
    private Double price;
    private String comment;
    private Date date;
    private String postalAdress;
    private int discountPercent;

    public InvoiceMessage() {
        //Default Constructor
    }

    public InvoiceMessage(Long tripid) {
        this.invoiceId = "MOD_" + tripid.toString();
        this.product = "Mobility on Demand";
        this.comment = "Thanks for flying with us! See you soon.";
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

    public String getPostalAdress() {
        return postalAdress;
    }

    public void setPostalAdress(String postalAdress) {
        this.postalAdress = postalAdress;
    }

    public void setPostalAdress(String firstname, String lastname, String adress) {
        this.postalAdress = firstname + " " + lastname + ", " + adress;
    }

	public Double getDistanceKilometres() {
		return distanceKilometres;
	}

	public void setDistanceKilometres(Double distanceKilometres) {
		this.distanceKilometres = distanceKilometres;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

}
