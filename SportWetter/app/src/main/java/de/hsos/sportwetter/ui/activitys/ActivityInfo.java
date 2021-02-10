package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.user.User;


public class ActivityInfo extends Fragment {

/*
    final static String DATA_RECEIVE = "data";
    long activityID = Long.parseLong(DATA_RECEIVE);
        */


      public ActivityInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

       Button joinBtn = view.findViewById(R.id.joinBtn);

       joinBtn.setOnClickListener(v->{
           activityInfo.addTeilnehmer(thisUser);
           dao.updateActivity(activityInfo);
           Toast.makeText(getContext(),thisUser.getUsername() + " wurde hinzugefügt",Toast.LENGTH_LONG).show();
       });


        return view;
    }
}