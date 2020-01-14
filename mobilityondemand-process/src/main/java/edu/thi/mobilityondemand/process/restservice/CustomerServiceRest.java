package edu.thi.mobilityondemand.process.restservice;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.thi.mobilityondemand.process.jpa.Customer;
import edu.thi.mobilityondemand.process.restservice.exceptions.BadRequestException;
import edu.thi.mobilityondemand.process.beans.CustomerServiceBean;

/**
 * Wrapper-Klasse um EJB CustomerServiceBean.
 * CustomerServiceRest stellt die externe REST-Schnittstelle zur Verfügung.
 * Diese REST-Services arbeiten mit einer Stateless Session EJB. Container übernimmt Threadverwaltung
 * --> kein Problem mit dem nicht threadsafen EntityManager, der im CustomerServiceBean verwendet wird!
 */
@Path("customers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerServiceRest {
    @Inject
    CustomerServiceBean customerService;
    
    /*
     * Lesen eines Kunden anhand seiner ID
     * Beispielaufruf aus Postman: GET auf http://localhost:8080/iis-customerhandling-war/resources/customers/1
     */
    @GET
    @Path("{id}")
    public Customer read(@PathParam("id") Long id) {
        return this.customerService.read(id);
    }
    
    /*
     * Suche nach Kunden
     * Beispielaufruf aus Postman: GET auf http://localhost:8080/iis-customerhandling-war/resources/customers?email=demo
     */
    @GET
    public Customer[] search(@QueryParam("email") String email) {
        return this.customerService.search(email);
    }
    
    /*
     * Anlegen eines neuen Kunden - akzeptiert wird JSON
     * Siehe auch Java Magazin-Artikel von Adam Bien 2/2017, S. 28
     * Erläuterungen zu UriInfo im Buch "Java EE 7" von Dirk Weil (2. Auflage), S. 299
     * Beispielaufruf aus Postman: POST auf http://localhost:8080/iis-customerhandling-war/resources/customers
     * Beispieldaten im Body: 
     * {
     *    "firstname": "Hans",
     *    "lastname": "Wurst",
     *    "email": "hans.wurst@demo.org",
     *    "yearOfBirth": 1999
     * }
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Customer customer, @Context UriInfo uriInfo) {
        Customer newCustomer = this.customerService.create(customer);
        Long id = newCustomer.getCustomerid();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
    /*
     * Aktualisierung eines Kunden - akzeptiert wird JSON
     * Bei PUT wird ein kompletter Kundendatensatz geliefert, der vollständig den alten Datensatz ersetzt
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") Long id, Customer customer) throws BadRequestException {
        if (id.longValue() != customer.getCustomerid().longValue())
            throw new BadRequestException("id mismatch");           // Ab JavaEE 7 ist BadRequestException Teil der JAX-RS Spec.
        this.customerService.update(customer);
    }
    
    /*
     * Löschen eines Kunden
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        this.customerService.delete(id);
    }
   
}
