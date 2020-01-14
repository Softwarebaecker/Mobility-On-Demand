/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.beans;

import edu.thi.mobilityondemand.process.jpa.Rating;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class RatingServiceBean implements RatingServiceBeanRemote, RatingServiceBeanLocal {

    @PersistenceContext
    EntityManager em;

    public Rating create(Rating rating) {
        em.persist(rating);
        return rating;
    }

    public Rating read(Long id) {
        return this.em.find(Rating.class, id);
    }

    public List<Rating> searchRatingWithNumberOfStars(int numberOfStars) {
        List<Rating> ratings = null;
        TypedQuery<Rating> query = em.createNamedQuery(Rating.searchRating, Rating.class);
        query.setParameter(1, numberOfStars);
        ratings = query.getResultList();
        return ratings;
    }
}
