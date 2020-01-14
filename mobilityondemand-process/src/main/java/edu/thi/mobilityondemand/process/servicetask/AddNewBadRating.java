// Sandro Käppner

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.beans.RatingServiceBean;
import edu.thi.mobilityondemand.process.jpa.Rating;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Stateless
@LocalBean
@Named
public class AddNewBadRating implements JavaDelegate, AddNewBadRatingLocal {

    @Inject
    RatingServiceBean ratingServiceBean;
    ArrayList<Rating> badRatings;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        badRatings = (ArrayList<Rating>) execution.getVariable("badRatings");
        if(badRatings == null) {
            badRatings = new ArrayList<>();
        }

        Long ratingId = (Long) execution.getVariable("ratingId");
        Rating rating = ratingServiceBean.read(ratingId);
        removeTooOldRatings();
        badRatings.add(rating);
        execution.setVariable("badRatings", badRatings);
        execution.setVariable("numberOfBadRatings", badRatings.size());
    }

    private void removeTooOldRatings() {
        LocalDateTime tooOldLocalDate = LocalDateTime.now().minusHours(24);
        Date tooOldDate = Date.from(tooOldLocalDate.atZone(ZoneId.systemDefault()).toInstant());
        badRatings.removeIf(rating -> rating.getSubmitTime().before(tooOldDate));
    }
}
