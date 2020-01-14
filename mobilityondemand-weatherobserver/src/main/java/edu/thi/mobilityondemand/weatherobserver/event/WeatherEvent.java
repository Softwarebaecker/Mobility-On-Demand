package edu.thi.mobilityondemand.weatherobserver.event;

import java.util.Date;

public class WeatherEvent {
    public enum WeatherType { SUNNY, CLOUDY, RAINY, STORMY}

    private final WeatherType weather;
    private final String location;
    private final Date time;

    public WeatherType getWeather() {
        return weather;
    }

    public String getLocation() {
        return location;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "WeatherEvent{" +
                "weather=" + weather +
                ", location='" + location + '\'' +
                ", time=" + time +
                '}';
    }

    public WeatherEvent(WeatherType weather, String location, Date time) {
        this.weather = weather;
        this.location = location;
        this.time = time;
    }
}
