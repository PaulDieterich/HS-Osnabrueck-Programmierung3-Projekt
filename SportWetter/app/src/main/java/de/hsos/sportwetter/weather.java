package de.hsos.sportwetter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.SearchView;
import android.widget.TextView;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class WeatherFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        OWM owm = new OWM(getString(R.string.openweather_api_key));
        SearchView searchView = new SearchView(this);
        WebView webView = new WebView(this);
        CharSequence input = searchView.getQuery();

        TextView stadtname = (TextView)findViewById(R.id.stadtname);
        TextView minTemp = (TextView)findViewById(R.id.minTemp);
        TextView maxTemp = (TextView)findViewById(R.id.maxTemp);

        try {
            CurrentWeather cwd = owm.currentWeatherByCityName(input.toString());
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    stadtname.setText(cwd.getCityName());
                }
                if(cwd.hasMainData() && cwd.getMainData().hasTempMin() && cwd.getMainData().hasTempMax()) {
                    minTemp.setText(cwd.getMainData().getTempMin() + "°C");
                    maxTemp.setText(cwd.getMainData().getTempMax() + "°C");
                }
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}