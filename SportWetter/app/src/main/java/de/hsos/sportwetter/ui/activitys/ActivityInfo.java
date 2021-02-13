package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;



import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.weather.City;


public class ActivityInfo extends Fragment {

    private static final int INTERNET_PERMISSION = 100;
    private List<Activity> activityList;

    OWM owm;
    CurrentWeather cwd;
    private City aktuelleStadt;
    int id;
    RecyclerViewAdapter recyclerViewAdapter;
    // Required empty public constructor
    ActivityInfoArgs args;
    public ActivityInfo() { }

    /**
     *sucht nach dem namen der aktivität, die zur übergebenen aktivitäts id past die übergeben wurde
     * in der datenbank und gibt den dort gefundenen aktivitäts orts namen in die Weather api ein und sucht nach
     * den wetter daten.
     * Diese werden dann im cwd objeckt gespeichert.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_info, container, false);       //welcher nutzer eingeloggt ist, informationen aus shardprefernces.
        User thisUser = Preferences.getInstance(getContext()).getUser();

        //holt sich die argumente aus dem den der ActivityFragment
        args = ActivityInfoArgs.fromBundle(getArguments());
        ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
        Activity activityInfo =  dao.getActivityById(args.getId());
        Log.d("ActivityDao", activityInfo.getName());

        /**
        *
        * initzialisierung vom owm objekt. mit den openweather_api_key
        */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.owm = new OWM(getString(R.string.openweather_api_key));
        owm.setUnit(OWM.Unit.METRIC);
        try {
            this.cwd = owm.currentWeatherByCityName(activityInfo.getStart().getPlaceName());
            this.aktuelleStadt = new City(this.cwd);
        } catch (APIException e) {
            e.printStackTrace();
        }

       TextView sunriseText = view.findViewById(R.id.sunrise_text);
       TextView sunsetText = view.findViewById(R.id.sunset_text);
       TextView rainText = view.findViewById(R.id.rain_text);
       TextView sunHoursText = view.findViewById(R.id.sunHours_text);
       TextView activityName = view.findViewById(R.id.activityName);
       activityName.setText(activityInfo.getName());
       TextView activityLoaction = view.findViewById(R.id.ActivityLocation);
       activityLoaction.setText(activityInfo.getStart().getPlaceName());

       Long sunHours =  TimeUnit.MILLISECONDS.toHours(cwd.getSystemData().getSunsetDateTime().getTime() - cwd.getSystemData().getSunriseDateTime().getTime());
       sunriseText.setText(SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(cwd.getSystemData().getSunriseDateTime()));
       sunsetText.setText(SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(cwd.getSystemData().getSunsetDateTime()));
       sunHoursText.setText(String.valueOf(sunHours) + " h");
       Button joinBtn = view.findViewById(R.id.joinBtn);

       /**
        * joinButton onClick
        *
        * update Activity database
        * */
       joinBtn.setOnClickListener(v->{
           activityInfo.addTeilnehmer(thisUser);
           dao.updateActivity(activityInfo);
           Toast.makeText(getContext(),thisUser.getUsername() + " wurde hinzugefügt",Toast.LENGTH_LONG).show();
       });
        return view;
    }
}