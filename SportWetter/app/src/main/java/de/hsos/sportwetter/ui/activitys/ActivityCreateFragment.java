package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.user.ActivityProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivityCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityCreateFragment extends Fragment {

    public ActivityCreateFragment() {
        // Required empty public constructor
    }


    public static ActivityCreateFragment newInstance(String param1, String param2) {
        ActivityCreateFragment fragment = new ActivityCreateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_create, container, false);

        TextView nameTV = (TextView) view.findViewById(R.id.ActivityName);
        TextView sportTV = (TextView) view.findViewById(R.id.sportName);
        TextView startTV = (TextView) view.findViewById(R.id.Activity_start);
        TextView zielTV = (TextView) view.findViewById(R.id.Activity_ziel);
        TextView dateTV = (TextView) view.findViewById(R.id.Activity_date);
        Button createBtn = (Button) view.findViewById(R.id.create_btn);

        //get input
        String name_S = nameTV.getText().toString();
        String sport_S = sportTV.getText().toString();
        String start_S = startTV.getText().toString();
        String ziel_S = zielTV.getText().toString();
        String date_S = dateTV.getText().toString();
        createBtn.setOnClickListener(v-> {
            //Create new Activity and add this into the activity database
            ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
            Activity a = createActivity(name_S, sport_S, start_S, ziel_S, date_S);
            dao.insertActivity(a);
        });
    return view;
    }

    public Activity createActivity(String name, String art, String ziel, String start, String date){
        Activity newActivity = new Activity();

        return newActivity;
    }

    public Location createLocation(){
        Location newLocation = new Location();
        return newLocation;
    }

    public Sport createSport(){
        Sport newsport = new Sport();
        return newsport;
    }

}