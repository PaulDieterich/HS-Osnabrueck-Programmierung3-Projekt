package de.hsos.sportwetter.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.ui.login.LoginActivity;
/**
 * @author Stefan Waschk
 */
public class SettingsFragment extends PreferenceFragmentCompat{

    NavController navController;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference profile = findPreference("profile");
        profile.setOnPreferenceClickListener(this::profile);

        Preference logout = findPreference("ausloggen");
        logout.setOnPreferenceClickListener(this::ausloggen);
    }
    /**
     * @param preference
     * @return boolean - true
     * Navigiert zum ProfileFragment.
     * */
    public boolean profile(Preference preference) {
        Navigation.findNavController(getView()).navigate(R.id.action_settingsFragment_to_profileFragment);
       return true;
    }

    /**
     * @param preference - preferences
     * @return boolean - true.
     *
     * Löscht den aktuell eingetragenen Nutzer aus den Preferences und ersetzt ihn durch null.
     * Navigiert anschließend zur LoginActivity.
     * */
    private boolean ausloggen(Preference preference){
        Preferences.getInstance(getContext()).setUser(null);
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        return true;
    }
}