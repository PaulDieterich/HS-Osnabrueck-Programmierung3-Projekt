package de.hsos.sportwetter.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import de.hsos.sportwetter.R;

public class LoginActivity extends Activity  {

    Button signIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

    }

}

