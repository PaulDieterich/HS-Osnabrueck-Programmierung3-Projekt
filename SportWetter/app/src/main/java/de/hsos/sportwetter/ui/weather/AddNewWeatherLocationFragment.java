package de.hsos.sportwetter.ui.weather;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import  android.widget.SearchView ;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;

import de.hsos.sportwetter.classes.weather.City;
import de.hsos.sportwetter.classes.weather.CityDao;

/**
 * AddNewWeatherLocationFragment
 * @author Stefan Waschk
 *
 * */
public class AddNewWeatherLocationFragment extends Fragment {
    private static String JSON_DATA = "javaapi.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    private RecyclerView rw;
    private CityViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private OWM owm;
    private CurrentWeather cwd;
    private List<String> cityList;
    private List<City> CityList;
    private City context;

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
        rw = view.findViewById(R.id.rv_main);
        cityList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CityViewModel.class);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), cityListUpdateObserver);
        recyclerViewAdapter = new RecyclerViewAdapter(context, getCursor(""));
        rw.setLayoutManager(new LinearLayoutManager(getContext()));
        rw.setAdapter(recyclerViewAdapter);

        SearchView searchView = (SearchView) view.findViewById(R.id.stadtsuche);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recyclerViewAdapter = new RecyclerViewAdapter(context, getCursor(newText));
                rw.setLayoutManager(new LinearLayoutManager(getContext()));
                rw.setAdapter(recyclerViewAdapter);
                return false;
            }
        });
        return view;
    }

    Observer<ArrayList<City>> cityListUpdateObserver = new Observer<ArrayList<City>>() {
        @Override
        public void onChanged(ArrayList<City> cityArrayList) {
            CityList = new ArrayList<>();
        }
    };
    /**
     * getCursor
     * @param text - String
     * @return Cursor
     *
     * Lädt die cityDao und speichert den dao.getCursor Aufruf in cursor.
     * */
    public Cursor getCursor(String text) {
        CityDao dao = AppDatabase.getDatabase(getContext()).cityDao();
        Cursor c = dao.getCursor(text+"%");
        return c;

    }
}