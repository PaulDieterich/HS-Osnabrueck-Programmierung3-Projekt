package de.hsos.sportwetter.classes.weather;

import androidx.annotation.NonNull;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

/**
 * To save Data from
 * https://openweathermap.org/api
 *
 * Example of current weather API response
 *      "temp":306.15, //current temperature
 *      "pressure":1013,
 *      "humidity":44,
 *      "temp_min":306, //min current temperature in the city
 *      "temp_max":306 //max current temperature in the city
 *
 * Example of daily forecast weather API response
 *         "day":297.77,  //daily averaged temperature
 *         "min":293.52, //daily min temperature
 *         "max":297.77, //daily max temperature
 *         "night":293.52, //night temperature
 *         "eve":297.77, //evening temperature
 *         "morn":297.77}, //morning temperature
 * */

public class Weather {

    private String stadtname;
    private String land;

    @NonNull
    private OWM owm;

    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    private double dailyAveragedTemp;
    private double dailyMinTemp;
    private double dailyMaxTemp;
    private double nightTemp;
    private double eveningTemp;
    private double morningTemp;

    public Weather(String apikey) {
        this.owm = new OWM(apikey);
        //TODO: Freie Auswahl der Einheit (Metric, Imperial, Kelvin)
        owm.setUnit(OWM.Unit.METRIC);
    }

    //TODO: Logik! Die UI Elemente werden in ui/weather/WeatherFragment behandelt!

    public void wetterAbfrage(String stadt) {
        try {
            CurrentWeather cwd = owm.currentWeatherByCityName(stadt);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                this.stadtname = cwd.getCityName();
                this.land = cwd.getSystemData().getCountryCode();
                this.temp = cwd.getMainData().getTemp();
                this.temp_min = cwd.getMainData().getTempMin();
                this.temp_max = cwd.getMainData().getTempMax();

                this.pressure = cwd.getMainData().getPressure();
                this.humidity = cwd.getMainData().getHumidity();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public String getStadtname() {
        return this.stadtname;
    }

    public String getLand() {
        return this.land;
    }

    public double getTempAvg() {
        return this.temp;
    }

    public double getTempMin() {
        return this.temp_min;
    }

    public double getTempMax() {
        return this.temp_max;
    }

}
