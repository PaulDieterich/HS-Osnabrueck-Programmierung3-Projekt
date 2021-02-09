package de.hsos.sportwetter.ui.activitys;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import de.hsos.sportwetter.classes.activity.Activity;



public class ActivityViewModel extends ViewModel {
    MutableLiveData<ArrayList<Activity>> activityLiveData;
    ArrayList<Activity> activityArrayList;

    public ActivityViewModel(){
        activityLiveData = new MutableLiveData<>();
        init();
    }
    MutableLiveData<ArrayList<Activity>> getMutableLiveData() {
        return activityLiveData;
    }
    private void init(){
        populateList();
        activityLiveData.setValue(activityArrayList);
    }
    public void populateList(){

        Activity a = new Activity();
        a.setName("n name");

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(a);
        activityList.add(a);
        activityList.add(a);
        activityList.add(a);
        activityList.add(a);

        activityArrayList = (ArrayList<Activity>) activityList;
    }

}
