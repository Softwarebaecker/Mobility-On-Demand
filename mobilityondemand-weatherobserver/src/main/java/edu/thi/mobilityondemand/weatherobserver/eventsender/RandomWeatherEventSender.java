/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.weatherobserver.eventsender;

import com.espertech.esper.client.EPRuntime;
import edu.thi.mobilityondemand.weatherobserver.event.WeatherEvent;

import java.util.Date;
import java.util.Random;

public class RandomWeatherEventSender implements Runnable {
    private final EPRuntime epRuntime;
    private final String location;

    public RandomWeatherEventSender(String location, EPRuntime epRuntime) {
        this.epRuntime = epRuntime;
        this.location = location;
    }

    @Override
    public void run() {
        Random random = new Random();
        WeatherEvent.WeatherType[] weatherValues = WeatherEvent.WeatherType.values();
        for (int i = 0; i < 1000; ++i) {
            WeatherEvent weatherEvent = new WeatherEvent(weatherValues[random.nextInt(weatherValues.length)], location, new Date());
            System.out.println(weatherEvent);
            epRuntime.sendEvent(weatherEvent);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
