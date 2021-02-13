package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.location.LocationDao;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.sport.SportDao;
import de.hsos.sportwetter.classes.user.User;

/**
 * @author Paul Dieterich
 * ActivityCreateFragement
 */
public class ActivityCreateFragment extends Fragment {

    public ActivityCreateFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_create, container, false);

        TextView nameTV = view.findViewById(R.id.ActivityName);
        TextView sportTV = view.findViewById(R.id.sportName);
        TextView startTV =  view.findViewById(R.id.Activity_start);
        TextView zielTV =  view.findViewById(R.id.Activity_ziel);
        TextView dateTV =  view.findViewById(R.id.Activity_date);
        Button createBtn = view.findViewById(R.id.create_btn);


        /**
         * Bei Klick auf den createBtn werden die Daten aus den Textfeldern gelesen und in Form von
         * String Objekten gespeichert. Die Funktion createActivity() wird aufgerufen und ein neues
         * Activity Objekt erstellt.
         * Dieses wird dann per ActivityDao.insertActivity() in die Datenbank geschrieben.
         * Danach wird man auf die Aktivitätsübersicht zurück navigiert.
         * */
        createBtn.setOnClickListener(v-> {
            String name_S = nameTV.getText().toString();
            String sport_S = sportTV.getText().toString();
            String start_S = startTV.getText().toString();
            String ziel_S = zielTV.getText().toString();
            String date_S = dateTV.getText().toString();
            //Create new Activity and add this into the activity database
            ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
            Activity a = createActivity(name_S, sport_S, start_S, ziel_S, date_S);
            dao.insertActivity(a);
            Toast.makeText(getContext(),"name:" + name_S,Toast.LENGTH_LONG);
            Navigation.findNavController(v).navigate(R.id.action_activity_createFragment_to_activityFragment2);
        });
    return view;
    }

    /**
     * @param name - String, Name der Aktivität
     * @param art - String, Name der Sportart
     * @param start - String, Name des Startortes
     * @param ziel  - String, Name des Zielortes
     * @param date  - String, Datum
     * @return Activity
     * Erstellt eine neue Aktivität.
     *
     * */
    public Activity createActivity(String name, String art, String ziel, String start, String date){
        Sport s_art = createSport(art);
        Location l_ziel = createLocation(ziel);
        Location l_start = createLocation(start);
        User p = new User();
        Activity newActivity = new Activity(name,p,s_art,l_start,l_ziel);
        return newActivity;
    }
    /**
     * createLocation
     * @param name - Der Name der Location
     * @return Location
     * Wenn der eingegebene Name der Location schon in der Location Datenbank steht, wird diese zurückgegeben.
     * Falls die Location nicht gefunden werden konnte, wird diese angelegt.
     * */
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
    /**
     * createSport
     * @param art Name der Sportart
     * @return Sport
     * Wenn der eingegebene Sportname schon in der Datenbank vorhanden ist, wird dieser zurückgegeben.
     * Falls noch kein Eintrag mit dem Namen vorliegt, wird ein neuer angelegt.
     * */
    public Sport createSport(String art){
        SportDao dao = AppDatabase.getDatabase(getContext()).sportDao();
        List<Sport> sportList =dao.getAllSports();
        for (Sport s : sportList ) {
            if(s.getName() == art){
                return s;
            }
        }
        Sport newsport = new Sport(art);
       // dao.insertSport(newsport);
        return newsport;
    }

}