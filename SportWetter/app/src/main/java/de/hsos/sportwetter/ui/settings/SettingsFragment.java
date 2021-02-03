package de.hsos.sportwetter.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import de.hsos.sportwetter.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}