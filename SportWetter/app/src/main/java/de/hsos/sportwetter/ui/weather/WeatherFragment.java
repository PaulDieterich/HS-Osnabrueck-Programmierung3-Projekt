package de.hsos.sportwetter.ui.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.DailyWeatherForecast;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;
import de.hsos.sportwetter.ui.activitys.ActivityInfoArgs;
/**
 *  @author Stefan Waschk
 * */
@RequiresApi(api = Build.VERSION_CODES.M)
public class WeatherFragment extends Fragment {

    private static final int INTERNET_PERMISSION = 100;
    OWM owm;
    CurrentWeather cwd;
    private City aktuelleStadt;
    String cityName = "Bremen"; //defalt wetterausgabe, wenn keine stadt gefunden werden kann.
    public WeatherFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        WeatherFragmentArgs args = WeatherFragmentArgs.fromBundle(getArguments());
        if(args.getCityName() != null){
            cityName = args.getCityName();
        }

        this.owm = new OWM(getString(R.string.openweather_api_key));
        owm.setUnit(OWM.Unit.METRIC);
        try {
            this.cwd = owm.currentWeatherByCityName(cityName);
            this.aktuelleStadt = new City(this.cwd);
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);
        TextView stadtname = (TextView) view.findViewById(R.id.stadtname);
        TextView land = (TextView) view.findViewById(R.id.land);
        TextView avgTemp = (TextView) view.findViewById(R.id.avgTemp);
        TextView minTemp = (TextView) view.findViewById(R.id.minTemp);
        TextView maxTemp = (TextView) view.findViewById(R.id.maxTemp);

        if(!(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION);
        }

        stadtname.setTextSize(30);
        stadtname.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        land.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        avgTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        minTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        maxTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        Handler handler = new Handler();
        Resources res = getResources();
        /**
         * holt sich die daten von der api und setzt diese in die vorgesehenden textfelder ein.
         * */
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                try {
                    cwd = owm.currentWeatherByCityName(aktuelleStadt.getName());
                    stadtname.setText(aktuelleStadt.getName());
                    land.setText(aktuelleStadt.getLand());
                    avgTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTemp()));
                    maxTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMax()));
                    minTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMin()));
                    // Log.d("Handlers", "Called on main thread");
                    // 'this' is referencing the Runnable object
                    handler.postDelayed(this, 5000);
                } catch (APIException e) {
                    e.printStackTrace();
                }
            }
        };
        handler.post(runnableCode);

        addBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_weatherFragment_to_addNewWeatherLocationFragment);
        });

        return view;
    }


}