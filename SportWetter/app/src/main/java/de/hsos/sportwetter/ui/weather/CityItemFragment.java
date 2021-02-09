package de.hsos.sportwetter.ui.weather;

import android.content.Context;
import android.os.BaseBundle;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.weather.Weather;
import de.hsos.sportwetter.ui.activitys.RecyclerViewAdapter;
import de.hsos.sportwetter.ui.weather.databinding.FragmentWeatherItemBinding;

import de.hsos.sportwetter.R;

public class CityItemFragment extends AppCompatActivity {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public WeatherItemFragment() { }

    public static WeatherItemFragment newInstance(int columnCount){
        WeatherItemFragment fragment = new WeatherItemFragment();
                return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //DAO

        View view = inflater.inflate(R.layout.fragment_weather_item, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new RecyclerViewAdapter();
        }
        return view;
    }
}