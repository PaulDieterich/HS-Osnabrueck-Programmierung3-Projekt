package de.hsos.sportwetter.ui.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

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

public class AddNewWeatherLocationFragment extends Fragment  {

    SearchView searchView;
    ListView listView;
    Handler handler;
    RecyclerView rw;
    WeatherViewModel viewModel;
    RecyclerViewAdapter recyclerViewAdapter;
    Weather context;
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
}