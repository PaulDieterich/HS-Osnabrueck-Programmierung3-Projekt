package de.hsos.sportwetter.ui.weather;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import de.hsos.sportwetter.classes.weather.City;

public class CityViewModel extends ViewModel {

    MutableLiveData<ArrayList<City>> cityLiveData;
    ArrayList<City> cityArrayList;

    public CityViewModel(){
        cityLiveData = new MutableLiveData<>();
        init();
    }
    MutableLiveData<ArrayList<City>> getMutableLiveData(){
        return cityLiveData;
    }

    private void init(){
        populateList();
        cityLiveData.setValue(cityArrayList);
    }
    public void populateList(){
        ArrayList<City> cityArrayListList = new ArrayList<>();
        cityArrayList = (ArrayList<City>) cityArrayListList;
    }
}
