package de.hsos.sportwetter.ui.weather;

import android.content.Context;
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

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void populateList(){
            City c = new City();
            c.setName("Bremen");
            c.setLand("Deuschland");
            City c1 = new City();
            c1.setName("Brauenschweig");
            c1.setLand("Deuschland");
            City c2 = new City();
            c2.setName("Bratislava");
            c2.setLand("Slowakei");
            ArrayList<City> cityList = new ArrayList<>();
            cityList.add(c);
            cityList.add(c1);
            cityList.add(c2);
            cityArrayList = cityList;
    }
}
