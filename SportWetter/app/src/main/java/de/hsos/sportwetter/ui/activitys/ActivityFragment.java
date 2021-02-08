package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;

public class ActivityFragment extends Fragment implements LifecycleOwner {

    Button createActivity;
    RecyclerView rw;
    ActivityViewModel viewModel;
    RecyclerViewAdapter recyclerViewAdapter;
    Activity context;
    RecyclerView recyclerView;
    public interface ActivityFragmentListener{
        void onInputActivitySent(Activity a);
    }
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
        return view;
    }
    Observer<ArrayList<Activity>> activityListUpdateObserver = new Observer<ArrayList<Activity>>() {
        @Override
        public void onChanged(ArrayList<Activity> activityArrayList) {
            ActivityDao dao = AppDatabase.getDatabase(getContext()).activityDao();
            activityArrayList = (ArrayList<Activity>) dao.getAllActivitys();
            recyclerViewAdapter  = new RecyclerViewAdapter(context,activityArrayList);
            rw.setLayoutManager(new LinearLayoutManager(getContext()));
            rw.setAdapter(recyclerViewAdapter);
        }
    };
}