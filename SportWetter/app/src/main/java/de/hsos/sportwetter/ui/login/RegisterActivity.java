package de.hsos.sportwetter.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import de.hsos.sportwetter.MainActivity;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    Button registerbtn;
    EditText username, password, email;
    String userName, passwd, eMail;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         sharedPreferences = getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        registerbtn = (Button) findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(v -> {
            this.userName = username.getText().toString();
            this.passwd = password.getText().toString();
            this.eMail = email.getText().toString();
            editor.putString("userName",userName);
            editor.putString("passwd", passwd);
            editor.putString("email",eMail);
            editor.commit();
            Toast.makeText(RegisterActivity.this, "added to sharedPreferences", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

}