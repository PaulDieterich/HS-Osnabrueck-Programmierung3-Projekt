package de.hsos.sportwetter.ui.weather;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;

import de.hsos.sportwetter.MainActivity;
import de.hsos.sportwetter.MainFragment;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.Weather;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class WeatherFragment extends Fragment implements View.OnClickListener {

    private static final int INTERNET_PERMISSION = 100;
    private Weather weather;
    private Handler handler;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    NavController navController;
    public WeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.weather = new Weather(getString(R.string.openweather_key));
        this.handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        TextView stadtname = (TextView) view.findViewById(R.id.stadtname);
        TextView land = (TextView) view.findViewById(R.id.land);
        TextView avgTemp = (TextView) view.findViewById(R.id.avgTemp);
        TextView minTemp = (TextView) view.findViewById(R.id.minTemp);
        TextView maxTemp = (TextView) view.findViewById(R.id.maxTemp);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);
        SearchView stadtsuche = (SearchView) view.findViewById(R.id.stadtsuche);

        if(ContextCompat.checkSelfPermission(
            this.getContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this.getContext(), "Internet permission already granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(
                    this.getActivity(), new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION
            );
        }

        stadtname.setTextSize(30);
        stadtname.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        land.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        avgTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        minTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        maxTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //TODO: Aktualisierung jede Stunde bei gueltiger Stadt oder wenn User neue Anfrage abfeuert
        //Thread thread = new Thread(() -> weather.wetterAbfrage());
        //thread.stadt();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                weather.wetterAbfrage(stadtsuche.getQuery().toString());
                stadtname.setText(weather.getStadtname());
                land.setText(weather.getLand());
                avgTemp.setText(Double.toString(weather.getTempAvg()) + " °C");
                minTemp.setText(Double.toString(weather.getTempMin()) + " °C");
                maxTemp.setText(Double.toString(weather.getTempMax()) + " °C");
                // Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 5 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnableCode);
<<<<<<< HEAD

=======
        
>>>>>>> origin/main
        return view;
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_weatherFragment_to_addNewWeatherLocationFragment);
    }
}