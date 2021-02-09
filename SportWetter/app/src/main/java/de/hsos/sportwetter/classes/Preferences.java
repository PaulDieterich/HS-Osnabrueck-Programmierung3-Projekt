package de.hsos.sportwetter.classes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.List;
import java.util.prefs.Preferences;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.user.UserDao;

public class Preference {

    private static Preferences instance;

    private final SharedPreferences sharedPreferences;
    private final UserDao userDao;

    private Preferences(Context applicationContext) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        this.userDao = AppDatabase.getDatabase(applicationContext).userDao();
    }
    public void setUser(User u){
        sharedPreferences.edit().putLong("userID", u.getUserID()).apply();
    }
    public User getUser(){
        long userID = sharedPreferences.getLong("userID",-1);
        if(userID > -1){
            for (User user : userDao.getAllUsers()) {
                if (user.getUserID() == userID) {
                    continue;
                }

            }
            return null;
        }else{
            return null;
        }
    }
    public static Preferences getInstance(Context applicationContext) {
        if(instance == null) instance = new Preferences(applicationContext);
        return instance;
    }

}
