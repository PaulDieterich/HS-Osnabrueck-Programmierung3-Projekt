package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.location.LocationDao;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.sport.SportDao;
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
            Navigation.findNavController(v).navigate(R.id.action_activity_createFragment_to_activityFragment2);
            Toast.makeText(getContext(),"name:" + name_S,Toast.LENGTH_LONG);
        });
    return view;
    }

    public Activity createActivity(String name, String art, String ziel, String start, String date){
        Sport s_art = createSport(art);
        Location l_ziel = createLocation(ziel);
        Location l_start = createLocation(start);
        ActivityProvider p = new ActivityProvider();
        Activity newActivity = new Activity(name,p,s_art,l_start,l_ziel);

        return newActivity;
    }
    public Location createLocation(String name){
        LocationDao dao = AppDatabase.getDatabase(getContext()).locationDao();
        List<Location> locationList =dao.getAllLocations();
        for (Location l : locationList ) {
            if(l.getPlaceName() == name){
                return l;
            }
        }
        Location newLocation = new Location(name);
       // dao.insertLocation(newLocation);
        return newLocation;
    }

    public Sport createSport(String art){
        SportDao dao = AppDatabase.getDatabase(getContext()).sportDao();
        List<Sport> sportList =dao.getAllSports();
        for (Sport s : sportList ) {
            if(s.getName() == art){
                return s;
            }
        }
        Sport newsport = new Sport(art);
    //    dao.insertSport(newsport);
        return newsport;
    }

}