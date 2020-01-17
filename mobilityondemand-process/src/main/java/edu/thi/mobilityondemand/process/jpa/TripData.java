/**
 * @author Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.process.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = TripData.searchCustomerTrips,
        query = "SELECT t FROM TripData t WHERE t.customerid=?1"),
    @NamedQuery(name = TripData.findAllTrips,
        query = "SELECT t FROM TripData t")
})
@Table(name = "TripData")
public class TripData implements Serializable {
    public final static String searchCustomerTrips = "TripData.searchCustomerTrips";
    public final static String findAllTrips = "TripData.findAllTrips";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripid;
    private Date startDate;
    private String startingpoint;
    private String endpoint;
    private Long customerid;
    private Double kilometers;
    private Double price;
    private boolean aborted;
    private boolean payed;


    public Long getTripid() {
        return tripid;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    /**
     * @author Daniel Schels
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * @author Daniel Schels
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * @author Daniel Schels
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @author Daniel Schels
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}

