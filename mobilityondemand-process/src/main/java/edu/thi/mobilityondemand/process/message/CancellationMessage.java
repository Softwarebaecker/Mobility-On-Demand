/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cancellationMessage")
public class CancellationMessage extends Message {
    private static final long serialVersionUID = 1L;

    private Long customerId;

    public CancellationMessage(Long customerId) {
        this.customerId = customerId;
        super.setText("We are sorry to inform you that your trip was cancelled.");
    }

    public CancellationMessage() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
