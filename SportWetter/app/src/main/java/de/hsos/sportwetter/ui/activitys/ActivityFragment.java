package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;

public class ActivityFragment extends Fragment{

    Button createActivity;
    public interface ActivityFragmentListener{
        void onInputActivitySent(Activity a);
    }

    public ActivityFragment() {
        // Required empty public constructor
    }
    int list_length = 10;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_item_list, container, false);
        for (int i = 0; i < list_length; i++) {
            view.setClickable(true);
            view.setFocusable(true);
            LinearLayout layout = view.findViewById(R.id.list);
            Button createActivity = (Button) view.findViewById(R.id.createActivity);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("DEBUG: klick on Activity");
                }
            });
            //final textView tv = (TextView) view.findViewById(R.id.)
            createActivity.setOnClickListener(v ->{
                Log.d("ActivityFragment: ", "createActivity button");
                Navigation.findNavController(v).navigate(R.id.action_activityFragment_to_activity_createFragment);
            });
            return view;
        }
        return view;
    }


}