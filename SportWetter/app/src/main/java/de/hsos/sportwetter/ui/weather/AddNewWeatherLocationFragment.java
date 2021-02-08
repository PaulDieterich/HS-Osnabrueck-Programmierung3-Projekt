package de.hsos.sportwetter.ui.weather;

import android.app.ListActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.LinearLayout;
=======
>>>>>>> 2101a8e (grammar tweaks)
=======
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
>>>>>>> 7987967 (rearranging weather completely)
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import org.jetbrains.annotations.NotNull;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.weather.Weather;
import de.hsos.sportwetter.ui.weather.WeatherViewModel;
import de.hsos.sportwetter.ui.weather.RecyclerViewAdapter;
=======
import de.hsos.sportwetter.R;
>>>>>>> 2101a8e (grammar tweaks)
=======
import java.util.ArrayList;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;
>>>>>>> 7987967 (rearranging weather completely)

public class AddNewWeatherLocationFragment extends Fragment implements SearchView.OnQueryTextListener {

<<<<<<< HEAD
    SearchView searchView;
    ListView listView;
    Handler handler;
    RecyclerView rw;
    WeatherViewModel viewModel;
    RecyclerViewAdapter recyclerViewAdapter;
    Weather context;
=======
    OWM owm;
    CurrentWeather cwd;
    SearchView stadtsuche;
    TextView stadt;
    ListView cityListViewer;
    ArrayList<City> cityList = new ArrayList<>();
    ListActivity la = new ListActivity();

>>>>>>> 7987967 (rearranging weather completely)
    public AddNewWeatherLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_add_new_location, container, false);
<<<<<<< HEAD
        rw = view.findViewById(R.id.rv_main);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(),weatherListUpdateObserver);

        return view;


         return view;
    }
<<<<<<< HEAD
    Observer<ArrayList<Activity>> weatherListUpdateObserver = new Observer<ArrayList<Weather>>() {
        @Override
        public void onChanged(ArrayList<Weather> weatherArrayList) {
            ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();

            recyclerViewAdapter  = new RecyclerViewAdapter(context,weatherArrayList);
            rw.setLayoutManager(new LinearLayoutManager(getContext()));
            rw.setAdapter(recyclerViewAdapter);
        }
    };

=======
>>>>>>> 2101a8e (grammar tweaks)
=======
        stadtsuche = (SearchView) view.findViewById(R.id.stadtsuche);
        cityListViewer = (ListView) view.findViewById(R.id.stadtliste);
        stadt = (TextView) view.findViewById(R.id.stadt);
        stadtsuche.setOnQueryTextListener(this);

        ArrayAdapter<City> aa = new ArrayAdapter<>(
                this.getContext(), R.layout.fragment_weather, R.id.stadt, cityList);

        cityListViewer = la.getListView();
        cityListViewer.setAdapter(aa);

        //https://www.tutorialspoint.com/how-to-handle-the-click-event-in-listview-in-android
        cityListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_weatherFragment);
            }
        });

        //TODO: do a list with all found citys that matches the search insert

         return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            cwd = owm.currentWeatherByCityName(query);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    City city = new City(this.cwd);
                    this.cityList.add(city);
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
>>>>>>> 7987967 (rearranging weather completely)
}