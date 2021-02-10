package de.hsos.sportwetter.ui.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import org.w3c.dom.Text;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.weather.City;


public class ActivityInfo extends Fragment {

/*
    final static String DATA_RECEIVE = "data";
    long activityID = Long.parseLong(DATA_RECEIVE);
        */
private static final int INTERNET_PERMISSION = 100;
    OWM owm;
    CurrentWeather cwd;
    private City aktuelleStadt;

      public ActivityInfo() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_activity_info, container, false);

       //welcher nutzer eingeloggt ist, informationen aus shardprefernces.
       User thisUser = Preferences.getInstance(getContext()).getUser();

       ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
       Activity activityInfo =  dao.getActivityById(1);

       TextView sunriseText = view.findViewById(R.id.sunrise_text);
       TextView sunsetText = view.findViewById(R.id.sunset_text);
       TextView rainText = view.findViewById(R.id.rain_text);
       TextView sunHoursText = view.findViewById(R.id.sunHours_text);

        TextView activityLoaction = view.findViewById(R.id.ActivityLocation);
        activityLoaction.setText(aktuelleStadt.getName().toString());

       int sunHours = (int) (cwd.getSystemData().getSunsetDateTime().getTime() - cwd.getSystemData().getSunriseDateTime().getTime());
       sunriseText.setText(cwd.getSystemData().getSunriseDateTime().toString());
       sunsetText.setText(cwd.getSystemData().getSunsetDateTime().toString());
       sunHoursText.setText(String.valueOf(sunHours) + " h");


       Button joinBtn = view.findViewById(R.id.joinBtn);

       joinBtn.setOnClickListener(v->{
           activityInfo.addTeilnehmer(thisUser);
           dao.updateActivity(activityInfo);
           Toast.makeText(getContext(),thisUser.getUsername() + " wurde hinzugefügt",Toast.LENGTH_LONG).show();
       });

        return view;
    }
}