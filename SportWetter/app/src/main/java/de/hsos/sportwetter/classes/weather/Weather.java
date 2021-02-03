package de.hsos.sportwetter.classes.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import de.hsos.sportwetter.R;

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

public class Weather extends AppCompatActivity {

    private String stadtname;

    private double temp;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;

    private double dailyAveragedTemp;
    private double dailyMinTemp;
    private double dailyMaxTemp;
    private double nightTemp;
    private double eveningTemp;
    private double morningTemp;


    //TODO: Business Logik! Die UI Elemente werden in ui/weather/WeatherFragment behandelt!

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_weather);

        OWM owm = new OWM(getString(R.string.openweather_api_key));

        try {
            CurrentWeather cwd = owm.currentWeatherByCityName("Braunschweig", OWM.Country.GERMANY);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    this.stadtname = cwd.getCityName();
                }
                if(cwd.hasMainData() && cwd.getMainData().hasTempMin() && cwd.getMainData().hasTempMax()) {
                    this.temp_min = cwd.getMainData().getTempMin();
                    this.temp_max = cwd.getMainData().getTempMax();
                }
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public String getStadtname() {
        return this.stadtname;
    }

    public double getTempMin() {
        return this.temp_min;
    }

    public double getTempMax() {
        return this.temp_max;
    }

}
