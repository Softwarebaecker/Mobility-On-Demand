/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.weatherobserver.controller;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import edu.thi.mobilityondemand.weatherobserver.event.WeatherEvent;
import edu.thi.mobilityondemand.weatherobserver.eventsender.RandomWeatherEventSender;
import edu.thi.mobilityondemand.weatherobserver.subscriber.StormyWeatherEventSubscriber;

public class WeatherEventController {

    public static void main(String[] args) {
        EPServiceProvider serviceProvider = EPServiceProviderManager.getDefaultProvider();

        serviceProvider.getEPAdministrator().getConfiguration().addEventType(WeatherEvent.class);

        StormyWeatherEventSubscriber stormyWeatherEventSubscriber = new StormyWeatherEventSubscriber();
        EPStatement stormyWeatherEventStatement = serviceProvider.getEPAdministrator().createEPL(stormyWeatherEventSubscriber.getStatement());
        stormyWeatherEventStatement.setSubscriber(stormyWeatherEventSubscriber);

        System.out.println("Starting WeatherEventSender-Thread");
        Thread eventSenderThread = new Thread(new RandomWeatherEventSender("A", serviceProvider.getEPRuntime()));
        eventSenderThread.start();
        Thread eventSenderThread2 = new Thread(new RandomWeatherEventSender("B", serviceProvider.getEPRuntime()));
        eventSenderThread2.start();
    }
}
