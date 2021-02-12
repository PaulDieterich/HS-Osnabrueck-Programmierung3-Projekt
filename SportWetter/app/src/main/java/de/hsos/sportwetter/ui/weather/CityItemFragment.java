package de.hsos.sportwetter.ui.weather;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import de.hsos.sportwetter.ui.activitys.RecyclerViewAdapter;

import de.hsos.sportwetter.R;

public class CityItemFragment extends AppCompatActivity {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public  CityItemFragment() { }

    public static CityItemFragment newInstance(int columnCount){
        CityItemFragment fragment = new  CityItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //DAO

        View view = inflater.inflate(R.layout.fragment_city_item, container, false);
        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.rv_main);
        // recyclerView.setAdapter(new RecyclerViewAdapter();
        return view;
    }

}