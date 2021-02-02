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

    private float temp;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int humidity;

    private float dailyAveragedTemp;
    private float dailyMinTemp;
    private float dailyMaxTemp;
    private float nightTemp;
    private float eveningTemp;
    private float morningTemp;


    //TODO: Business Logik! Die UI Elemente werden in ui/weather/WeatherFragment behandelt!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_weather);

        OWM owm = new OWM(getString(R.string.openweather_api_key));
        SearchView searchView = (SearchView)findViewById(R.id.stadtsuche);
        CharSequence input = searchView.getQuery();

        TextView stadtname = (TextView)findViewById(R.id.stadtname);
        TextView minTemp = (TextView)findViewById(R.id.minTemp);
        TextView maxTemp = (TextView)findViewById(R.id.maxTemp);

        try {
            CurrentWeather cwd = owm.currentWeatherByCityName("Braunschweig", OWM.Country.GERMANY);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    stadtname.setText(cwd.getCityName());
                }
                if(cwd.hasMainData() && cwd.getMainData().hasTempMin() && cwd.getMainData().hasTempMax()) {
                    minTemp.setText(cwd.getMainData().getTempMin().toString());
                    maxTemp.setText(cwd.getMainData().getTempMax().toString());
                }
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

}
