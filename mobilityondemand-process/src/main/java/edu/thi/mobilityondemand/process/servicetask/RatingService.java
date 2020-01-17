/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.beans.RatingServiceBean;
import edu.thi.mobilityondemand.process.jpa.Rating;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Stateless
@LocalBean
@Named
public class RatingService implements RatingServiceLocal {
    @Inject
    RatingServiceBean ratingServiceBean;

    public void createRating(DelegateExecution execution) {
        Long customerId = (Long) execution.getVariable("customerId");
        Long tripId = (Long) execution.getVariable("tripId");
        int numberOfStars = (int) execution.getVariable("numberOfStars");
        String comment = (String) execution.getVariable("comment");

        Rating rating = new Rating();
        rating.setCustomerId(customerId);
        rating.setTripId(tripId);
        rating.setNumberOfStars(numberOfStars);
        rating.setComment(comment);
        rating.setSubmitTime(new Date());
        ratingServiceBean.create(rating);
        execution.setVariable("ratingId", rating.getRatingId());
    }
}
