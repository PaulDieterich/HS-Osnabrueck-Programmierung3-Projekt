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

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class WeatherFragment extends Fragment {

    private static final int INTERNET_PERMISSION = 100;
    private OWM owm;
    private CurrentWeather cwd;
    private City aktuelleStadt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance(String param1, String param2) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.owm = new OWM(getString(R.string.openweather_api_key));
        owm.setUnit(OWM.Unit.METRIC);
        try {
            this.cwd = owm.currentWeatherByCityName("Braunschweig");
            this.aktuelleStadt = new City(this.cwd);
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        //TODO: Aufruf der Logik, vielleicht als Background Task

        Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                Resources res = getResources();
                stadtname.setText(aktuelleStadt.getName());
                land.setText(aktuelleStadt.getLand());
                avgTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTemp()));
                maxTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMax()));
                minTemp.setText(String.format(res.getString(R.string.temperature),cwd.getMainData().getTempMin()));
                // Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 5 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnableCode);

        addBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_weatherFragment_to_addNewWeatherLocationFragment);
        });

        return view;
    }

    private void setAktuelleStadt(City auswahlStadt) {
        this.aktuelleStadt = auswahlStadt;
    }
}