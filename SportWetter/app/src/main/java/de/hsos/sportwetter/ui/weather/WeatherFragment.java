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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import de.hsos.sportwetter.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class WeatherFragment extends Fragment {

    private static final int INTERNET_PERMISSION = 100;
    OWM owm;
    CurrentWeather cwd;
    private Handler handler;
    TextView stadtname, land, avgTemp, minTemp, maxTemp;
    Button addBtn;
<<<<<<< HEAD
=======

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
>>>>>>> 7987967 (rearranging weather completely)

    NavController navController;
    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.owm = new OWM(getString(R.string.openweather_api_key));
        owm.setUnit(OWM.Unit.METRIC);
        this.cwd = new CurrentWeather();
        this.handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        this.addBtn = (Button) view.findViewById(R.id.add_btn);
        this.stadtname = (TextView) view.findViewById(R.id.stadtname);
        this.land = (TextView) view.findViewById(R.id.land);
        this.avgTemp = (TextView) view.findViewById(R.id.avgTemp);
        this.minTemp = (TextView) view.findViewById(R.id.minTemp);
        this.maxTemp = (TextView) view.findViewById(R.id.maxTemp);

        if(!(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION);
        }

        stadtname.setTextSize(30);
        stadtname.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        land.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        avgTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        minTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        maxTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //TODO: Aufruf der Logik, vielleicht als Background Task
        //Thread thread = new Thread(() -> weather.wetterAbfrage());
        //thread.stadt();

        /*
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                weather.wetterAbfrage(stadtsuche.getQuery().toString());
                stadtname.setText(weather.getStadtname());
                land.setText(weather.getLand());
                avgTemp.setText(weather.getTempAvg() + weather.getUnit());
                minTemp.setText(weather.getTempMin() + weather.getUnit());
                maxTemp.setText(weather.getTempMax() + weather.getUnit());
                // Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 5 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnableCode);
         */

        addBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_weatherFragment_to_addNewWeatherLocationFragment);
        });

        return view;
    }
}