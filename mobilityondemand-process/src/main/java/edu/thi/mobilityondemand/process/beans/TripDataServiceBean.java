package edu.thi.mobilityondemand.process.beans;

import edu.thi.mobilityondemand.process.jpa.TripData;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class TripDataServiceBean implements TripDataServiceBeanRemote, TripDataServiceBeanLocal {

    @PersistenceContext
    EntityManager em;

    public TripData create(TripData tripData) {
        em.persist(tripData);
        return tripData;
    }

    public TripData read(Long id) {
        return this.em.find(TripData.class, id);
    }

    public List<TripData> searchCustomerTrips(Long customerid){
        List<TripData> trips = null;
        TypedQuery<TripData> query = em.createNamedQuery(TripData.searchCustomerTrips, TripData.class);
        query.setParameter(1, customerid);
        trips = query.getResultList();
        return trips;
    }


}
