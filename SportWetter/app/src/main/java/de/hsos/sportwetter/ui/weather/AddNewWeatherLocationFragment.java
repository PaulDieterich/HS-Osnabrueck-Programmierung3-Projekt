package de.hsos.sportwetter.ui.weather;

import android.app.ListActivity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.ActivityDao;

import de.hsos.sportwetter.classes.weather.City;

public class AddNewWeatherLocationFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView rw;
    CityViewModel viewModel;
    OWM owm;
    CurrentWeather cwd;
    List<String> cityList;

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
        viewModel = new ViewModelProvider(this).get(CityViewModel.class);
        //viewModel.getMutableLiveData().observe(getViewLifecycleOwner(),cityListUpdateObserver);

        //https://www.tutorialspoint.com/how-to-handle-the-click-event-in-listview-in-android

        //stadtsuche = (SearchView) view.findViewById(R.id.stadtsuche);
        ListView cityListViewer = (ListView) view.findViewById(R.id.stadtliste);
        //stadtsuche.setOnQueryTextListener(this);

        String[] staedte = new String[] {"Braunschweig", "Bremen", "Bratislava"};
        this.cityList = new ArrayList<>(Arrays.asList(staedte));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, cityList);

        cityListViewer.setAdapter(arrayAdapter);

        cityListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Navigation.findNavController(getView()).navigate(R.id.action_addNewWeatherLocationFragment_to_weatherFragment);
            }
        });

        return view;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

        //TODO: do a list with all found citys that matches the search insert

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            cwd = owm.currentWeatherByCityName(query);
            if(cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if(cwd.hasCityName()) {
                    this.cityList.add(cwd.getCityName());
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
}