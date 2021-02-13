package de.hsos.sportwetter.ui.weather;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Ignore;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Stefan Waschk
 * */
public class CityViewModel extends ViewModel {
    private RequestQueue mQueue;
    MutableLiveData<ArrayList<City>> cityLiveData;
    ArrayList<City> cityArrayList;
    Context context;


    public CityViewModel(){
        cityLiveData = new MutableLiveData<>();
        init();
    }
    MutableLiveData<ArrayList<City>> getMutableLiveData(){
        return cityLiveData;
    }
    public void selectItem(ArrayList<City> item) {
        cityLiveData.setValue(item);
    }
    private void init(){
        populateList();
        cityLiveData.setValue(cityArrayList);
    }


    /**
    *für vorstellungs zwecke, um alle Städte aus der datenbank(openweather api) nutzen zu dürfen
    *muss man monatlich zahlen, also haben wir eine kleine auswahl an stäten nach den man suchen kann.
    */
     public void populateList(){

/*
        City c0 = new City("Bremen","Deutschland");
        City c1 = new City("Brauenschweig","Deutschland");
        City c2 = new City("London", "Großbritanien");
        City c3 = new City("Berlin", "Deutschland");
        City c4 = new City("Paries", "Frankreich");
        City c5 = new City("Kiel", "Deutschland");
        City c6 = new City("Osnabrueck","Deutschland");
            ArrayList<City> cityList = new ArrayList<>();
            cityList.add(c0);
            cityList.add(c1);
            cityList.add(c2);
            cityList.add(c3);
            cityList.add(c4);
            cityList.add(c5);
            cityList.add(c6);
            cityArrayList = cityList;
            */

    }

}
