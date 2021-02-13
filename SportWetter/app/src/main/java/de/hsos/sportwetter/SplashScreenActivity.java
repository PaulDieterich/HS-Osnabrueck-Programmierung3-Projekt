package de.hsos.sportwetter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.databinding.ActivitySplashScreenBinding;
import de.hsos.sportwetter.ui.login.LoginActivity;

/**
 * Paul Dieterich
 *
 * */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int stop = 10000;
        /**
        *
        * Handler() ist veraltet, aber das war die schnellste und einfachste Lösung auf die ich
        * gekommen bin.
        * */
        @SuppressWarnings("deprecation") Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scheduleSplachscreen();
            }
        },stop);
    }
    /***
     * schedulerSplachscreen
     * Wenn ein Preferences User gespeichert ist,
     * wird er direkt an die mainActivity weitergeleitet.
     * Wenn kein User gespeichert ist, wird der Nutzer beim starten
     * an die LoginActivity weitergeleitet.
     */
    private void scheduleSplachscreen(){
        if(Preferences.getInstance(this).getUser() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            //zu login weiterleiten
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}