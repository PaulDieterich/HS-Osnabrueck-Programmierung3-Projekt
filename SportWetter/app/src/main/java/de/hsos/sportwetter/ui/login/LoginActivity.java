package de.hsos.sportwetter.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.service.autofill.RegexValidator;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    Button login, register;
    EditText username, password;
    String userName, passwd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    NavController navController;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sharedPreferences = getApplicationContext().getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(v -> {
            Log.e("LOGIN: ", username.getText().toString());
            userName = username.getText().toString();
            passwd = password.getText().toString();
            String uName, uPass;
            uName = sharedPreferences.getString("userName", "");
            uPass = sharedPreferences.getString("passwd", "null");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
           if(userName.equals(uName) && passwd.equals(uPass)){
                Log.e("LOGIN: ", "X");
                Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
            }else{
                Toast.makeText(this,"LoginFaild",Toast.LENGTH_SHORT).show();
            }

        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        });

    }

}