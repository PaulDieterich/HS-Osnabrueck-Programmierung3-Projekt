package de.hsos.sportwetter.ui.settings;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceFragmentCompat;

import de.hsos.sportwetter.R;

public class SettingsFragment extends PreferenceFragmentCompat implements View.OnClickListener{

    NavController navController;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_settingsFragment_to_profileFragment);
    }
}