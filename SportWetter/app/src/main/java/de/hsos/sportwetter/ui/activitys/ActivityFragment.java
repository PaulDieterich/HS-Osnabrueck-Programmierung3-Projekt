package de.hsos.sportwetter.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
/**
 *
 *
 * */
public class ActivityFragment extends Fragment implements LifecycleOwner {
    RecyclerView rw;
    ActivityViewModel viewModel;
    RecyclerViewAdapter recyclerViewAdapter;
    Activity context;


    public ActivityFragment() {
        // Required empty public constructor
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        Button createActivity = view.findViewById(R.id.createActivity);
        rw = view.findViewById(R.id.rv_main);
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(),activityListUpdateObserver);
        createActivity.setOnClickListener(v ->{
            Log.d("ActivityFragment: ", "createActivity button");
            Navigation.findNavController(v).navigate(R.id.action_activityFragment_to_activity_createFragment);
        });
        //DAO
        ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
        List<Activity> activityList = dao.getAllActivitys();
        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.rv_main);
        recyclerView.setAdapter(new RecyclerViewAdapter(new Activity(),activityList));

        return view;

    }
    /**
     * activityListUpdateObserver
     */
    Observer<ArrayList<Activity>> activityListUpdateObserver = new Observer<ArrayList<Activity>>() {
        @Override
        /**
         * activityListUpdateObserver onChabged
         * @params activityArrayList - ArrayList<Activity>
         * Es wird eine Arrayliste erstellt und von der dao mit dao.getALlActivitys() gefüllt.
         * Es wird der RecyclerViewAdapter initialisiert und mit der eben erstellten Liste gefüllt.
         * Der RecyvlerViewAdapter wird dann an die RecyclerView rw (wurde nicht mehr geändert, danke LRS)
         * mit rw.setAdapter übergeben.
         */
        public void onChanged(ArrayList<Activity> activityArrayList) {
            ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
            activityArrayList = (ArrayList<Activity>) dao.getAllActivitys();
            recyclerViewAdapter  = new RecyclerViewAdapter(context,activityArrayList);
            rw.setLayoutManager(new LinearLayoutManager(getContext()));
            rw.setAdapter(recyclerViewAdapter);

        }
    };

}