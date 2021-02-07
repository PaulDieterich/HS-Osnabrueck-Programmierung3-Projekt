package de.hsos.sportwetter.ui.activitys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hsos.sportwetter.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link activity_createFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class activity_createFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public activity_createFragment() {
        // Required empty public constructor
    }


    public static activity_createFragment newInstance(String param1, String param2) {
        activity_createFragment fragment = new activity_createFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_activity_create, container, false);
        TextView nameTV = (TextView) view.findViewById()

    return view;
    }
}