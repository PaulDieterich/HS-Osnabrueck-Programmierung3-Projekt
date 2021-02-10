package de.hsos.sportwetter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.databinding.ActivitySplashScreenBinding;
import de.hsos.sportwetter.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Preferences.getInstance(this).getUser() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            //zu login weiterleiten
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


}