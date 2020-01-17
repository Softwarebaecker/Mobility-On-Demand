/**
 * @author Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.process.message;


import org.apache.camel.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "LinkedHashMap")
public class GeoDataMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long tripId;
    private String Location;
    private Double latitude;
    private Double longitude;

    @XmlElement(name = "tripId")
    public void set_tripId(Long tripId) {
        this.tripId = tripId;
    }

    public Double getLatitude() {
        return latitude;
    }

    @XmlElement(name = "latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @XmlElement(name = "longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getLocation() {
        return Location;
    }

    @XmlElement(name = "Location")
    public void setLocation(String location) {
        Location = location;
    }

    public static GeoDataMessage fromString(String raw) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(GeoDataMessage.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (GeoDataMessage) unmarshaller.unmarshal(new StringSource(raw));
    }
}
