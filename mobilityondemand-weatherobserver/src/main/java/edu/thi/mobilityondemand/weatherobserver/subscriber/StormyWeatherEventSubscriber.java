package edu.thi.mobilityondemand.weatherobserver.subscriber;

import edu.thi.mobilityondemand.weatherobserver.event.WeatherEvent;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Map;

public class StormyWeatherEventSubscriber {

    public String getStatement() {
        String stormyWeatherEventExpression =
                "select * " +
                "from WeatherEvent " +
                "match_recognize (" +
                "   partition by location" +
                "   measures A[2] as weatherevent" +
                "   pattern (A{3})" +
                "   define" +
                "       A as A.weather.toString() = 'STORMY')";
        return stormyWeatherEventExpression;
    }

    public void update(Map<String, WeatherEvent> map)  {
        WeatherEvent event = map.get("weatherevent");
        System.out.println("===============");
        System.out.println(event);
        System.out.println("===============");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("StormyWeatherEventQueue");
            MessageProducer producer = session.createProducer(destination);
            Message message = session.createMessage();
            message.setStringProperty("location", event.getLocation());
            message.setStringProperty("weather", event.getWeather().toString());

            producer.send(message);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
