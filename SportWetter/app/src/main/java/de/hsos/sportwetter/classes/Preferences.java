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
    private final Context context;

    private Preferences(Context applicationContext) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        context = applicationContext;

    }
    /**
     * setFirstStart()
     * @param firstStart - boolean, wenn true. dann wird der shardpreference key "firstStart auf true gesetzt. bzw auf false.
     * */
    public void setFirstStart(boolean firstStart){
        sharedPreferences.edit().putBoolean("firstStart", firstStart).apply();
    }
    /**
     * getFirstStart
     * @return boolean - returnt den startus fron "firstStart", default ist true
     * */
    public boolean getFirstStart(){
        return sharedPreferences.getBoolean("firstStart",true);
    }
    /**
     * setUser
     * @param u - User die UserID des users wird in sharedPreferences gespeichert
     * defalut ist -1, wenn kein nutzer eingeloggt wird wird bei dem aufrüf -1 in den key "userID" gespeichert
     * */
    public void setUser(User u){
        if(u == null) {
            sharedPreferences.edit().putLong("userID", -1).apply();
        } else {
            sharedPreferences.edit().putLong("userID", u.getUserID()).apply();
        }
    }
    /**
     * getUser
     * @return User - der User der aktuell eingeloggt ist. wenn kein user eingeloggt ist, wird null zurück gegeben
     * */
    public User getUser(){
        UserDao userDao = AppDatabase.getDatabase(context).userDao();
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
    /**
     * getInstance
     * @return Preferences - es wird eine Preferences instance erzeigt. und diese zurück gegeben
     *
     * */
    public static Preferences getInstance(Context applicationContext) {
        if(instance == null) instance = new Preferences(applicationContext);
        return instance;
    }

}
