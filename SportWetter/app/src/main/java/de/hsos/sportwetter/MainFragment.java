package de.hsos.sportwetter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;


public class MainFragment extends Fragment implements View.OnClickListener {



    public MainFragment() {
        // Required empty public constructor
    }

    NavController navController = null;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.weather_btn).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
       //     case R.id.weather_btn: navController.navigate(R.id.action_mainFragment_to_weatherFragment);
        }
    }
}

