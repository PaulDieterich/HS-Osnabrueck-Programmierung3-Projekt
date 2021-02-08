package de.hsos.sportwetter.ui.weather;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.classes.weather.Weather;

public class WeatherViewModel extends ViewModel {
    MutableLiveData<ArrayList<Weather>> weatherLiveData;
    ArrayList<Weather> weatherArrayList;

    public WeatherViewModel(){
        weatherArrayList = new MutableLiveData<>();
        init();
    }
    MutableLiveData<ArrayList<Weather>> getMutableLiveData(){ return weatherLiveData;}

    private void init(){
        populateList();
        weatherLiveData.setValue(weatherArrayList);
    }
    public void populateList(){
        Weather w = new Weather();


        ArrayList<Weather> weatherList = new ArrayList<>();

        weatherArrayList = (ArrayList<Weather>) weatherList;

    }
}
