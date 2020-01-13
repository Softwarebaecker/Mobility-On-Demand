package edu.thi.mobilityondemand.camel;

import edu.thi.mobilityondemand.camel.routes.RouteBuilderBankToCamunda;
import edu.thi.mobilityondemand.camel.routes.RouteBuilderCamundaToCustomer;
import edu.thi.mobilityondemand.camel.routes.RouteBuilderCamundaToInvoicePrinter;
import edu.thi.mobilityondemand.camel.routes.RouteBuilderSendTripToTaxi;
import edu.thi.mobilityondemand.camel.routes.RouteBuilderTaxiToCamunda;
import edu.thi.mobilityondemand.camel.routes.RouteBuilderWeatherToCamunda;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import javax.jms.ConnectionFactory;

/**
 * A Camel Application
 */
public class MainApp extends Main {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        MainApp main = new MainApp();
        main.run();
    }

    public MainApp() {
        super();
        initialize();
        createRouteBuilder();
    }

    private void initialize() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        this.bind("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    }

    private void createRouteBuilder() {

        addRouteBuilder(new RouteBuilderCamundaToCustomer());
        addRouteBuilder(new RouteBuilderSendTripToTaxi());
        addRouteBuilder(new RouteBuilderTaxiToCamunda());
        addRouteBuilder(new RouteBuilderWeatherToCamunda());
        addRouteBuilder(new RouteBuilderCamundaToInvoicePrinter());
        addRouteBuilder(new RouteBuilderBankToCamunda());
    }

}

