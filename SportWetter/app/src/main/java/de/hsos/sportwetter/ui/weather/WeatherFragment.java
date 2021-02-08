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
public class WeatherFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final int INTERNET_PERMISSION = 100;
    OWM owm;
    CurrentWeather cwd;
    private Handler handler;
    TextView stadtname, land, avgTemp, minTemp, maxTemp;
    SearchView stadtsuche;
    Button addBtn;

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
        addBtn = (Button) view.findViewById(R.id.add_btn);
        this.stadtname = (TextView) view.findViewById(R.id.stadtname);
        this.land = (TextView) view.findViewById(R.id.land);
        this.avgTemp = (TextView) view.findViewById(R.id.avgTemp);
        this.minTemp = (TextView) view.findViewById(R.id.minTemp);
        this.maxTemp = (TextView) view.findViewById(R.id.maxTemp);
        this.addBtn = (Button) view.findViewById(R.id.add_btn);
        this.stadtsuche = (SearchView) view.findViewById(R.id.stadtsuche);
        stadtsuche.setOnQueryTextListener(this);

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        Resources res = getResources();
        try {
            cwd = owm.currentWeatherByCityName(query);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    stadtname.setText(cwd.getCityName());
                    land.setText(cwd.getSystemData().getCountryCode());
                    minTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMin()));
                    maxTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMax()));
                    avgTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTemp()));
                    return true;
                } else {
                    Toast.makeText(this.getActivity(),"Stadt nicht gefunden.",Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this.getActivity(),"OpenWeather nicht erreichbar - bitte später erneut versuchen!.",Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}