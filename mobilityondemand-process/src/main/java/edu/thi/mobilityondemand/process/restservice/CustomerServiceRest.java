/**
 * @author Daniel Schels
 * 
 * Based on IIS Script WS2019/20 (by Volker Stiehl)
 */

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

@Path("customers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerServiceRest {
    @Inject
    CustomerServiceBean customerService;
    
    /*
     * Lesen eines Kunden anhand seiner ID
     */
    @GET
    @Path("{id}")
    public Customer read(@PathParam("id") Long id) {
        return this.customerService.read(id);
    }
    
    /*
     * Suche nach Kunden
     */
    @GET
    public Customer[] search(@QueryParam("email") String email) {
        return this.customerService.search(email);
    }
    
    /*
     * Anlegen eines neuen Kunden - akzeptiert wird JSON
     * Siehe auch Java Magazin-Artikel von Adam Bien 2/2017, S. 28
     * Erläuterungen zu UriInfo im Buch "Java EE 7" von Dirk Weil (2. Auflage), S. 299
     * Beispielaufruf aus Postman: POST auf {{host}}/resources/customers
     * Beispieldaten im Body: 
	 *	{
	*	    "firstname": "Marc",
	*	    "lastname": "Macher",
	*	    "email": "ma@demo.org",
	*	    "yearOfBirth": 1989,
	*	    "adress" : "Esplanade 10, 85049 Ingolstadt",
	*	    "discountGroup" : "student"
	*	}
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
