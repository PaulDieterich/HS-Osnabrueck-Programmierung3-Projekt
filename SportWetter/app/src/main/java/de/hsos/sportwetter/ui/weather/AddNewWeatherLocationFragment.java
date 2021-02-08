package de.hsos.sportwetter.ui.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.Weather;

public class AddNewWeatherLocationFragment extends Fragment  {


    SearchView searchView;
    ListView listView;
    Weather weather;
    Handler handler;

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
        searchView = (SearchView) view.findViewById(R.id.searchView);
        listView = (ListView) view.findViewById(R.id.listView);

        //TODO: do a ist with all found citys that matches the search insert

         return view;
    }


}