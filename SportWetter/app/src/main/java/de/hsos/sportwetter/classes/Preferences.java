package de.hsos.sportwetter.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.user.UserDao;

public class Preferences {

    private static Preferences instance;

    private final SharedPreferences sharedPreferences;
    private final UserDao userDao;

    private Preferences(Context applicationContext) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        this.userDao = AppDatabase.getDatabase(applicationContext).userDao();
    }
    public void setUser(User u){
        if(u == null) {
            sharedPreferences.edit().putLong("userID", -1).apply();
        } else {
            sharedPreferences.edit().putLong("userID", u.getUserID()).apply();
        }
    }
    public User getUser(){
        long userID = sharedPreferences.getLong("userID",-1);
        if(userID > -1){
            for (User user : userDao.getAllUsers()) {
                Log.d("getUser", user.getUserID() + " - " + userID);
                if (user.getUserID() == userID) {
                    return user;
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
