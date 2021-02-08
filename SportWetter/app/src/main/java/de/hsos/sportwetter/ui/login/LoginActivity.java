package de.hsos.sportwetter.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import de.hsos.sportwetter.AppDatabase;
import de.hsos.sportwetter.MainActivity;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.user.UserDao;

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
            String userName = username.getText().toString();
            String passwd = password.getText().toString();
           if(isAUser()){
               Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
            }else{
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
    private boolean isAUser(){
        UserDao dao = AppDatabase.getDatabase(this).userDao();
        List<User> userList = dao.getAllUsers();
        boolean isUser = false;
        for ( User u : userList) {
            if(u.getUsername() == userName){
                if(u.getPassword() == passwd){
                    isUser = true;
                }else{
                    Toast.makeText(this,"Password falsch",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"username nicht vorhanden",Toast.LENGTH_LONG).show();
            }
        }
        return isUser;
    }

}