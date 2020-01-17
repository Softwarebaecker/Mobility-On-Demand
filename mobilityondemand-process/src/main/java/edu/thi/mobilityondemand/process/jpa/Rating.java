/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(name = Rating.searchRating,
        query = "SELECT r FROM Rating r WHERE r.numberOfStars=?1")
@Table(name = "Rating")
public class Rating implements Serializable {
    public final static String searchRating = "Rating.searchRating";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    private Long tripId;
    private Long customerId;
    private int numberOfStars;
    private String comment;
    private Date submitTime;

    public Long getRatingId() {
        return ratingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
