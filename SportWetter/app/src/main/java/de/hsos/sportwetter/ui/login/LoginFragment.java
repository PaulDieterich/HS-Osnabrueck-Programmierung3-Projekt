package de.hsos.sportwetter.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.hsos.sportwetter.MainActivity;
import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.Callback;

public class LoginFragment extends Fragment implements View.OnClickListener{



        Button login, register;
        EditText username, password;
        String userName, passwd;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        NavController navController;
        public void onAttach(Context context) {
                sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                super.onAttach(context);
        }
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedIntstanceState){
        View view = inflater.inflate(R.layout.fragment_login,contrainer,false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        login = (Button) view.findViewById(R.id.login);
        register = (Button) view.findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        return view;
    }

        private void login(View v){
                Log.e("LOGIN: ", username.getText().toString());
                userName = username.getText().toString();
                passwd = password.getText().toString();
                String uName, uPass;
                uName = sharedPreferences.getString("userName", null);
                uPass = sharedPreferences.getString("passwd", null);

                if(userName.equals(uName) && passwd.equals(uPass)){
                        Log.e("LOGIN: ", "X");
                        Toast.makeText(getContext(), "Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginFragment.this.getContext(), MainActivity.class);
                        startActivity(intent);
                }else{
                        Toast.makeText(getContext(),"LoginFaild",Toast.LENGTH_SHORT).show();
                }
        }
        @Override
        public void onClick(View v) {
        switch (v.getId()){
                case R.id.login : login(v); break;
                case R.id.register :
                        System.out.println("FUCJASD");
                        navController.navigate(R.id.action_loginFragment_to_registerFragment); break;
        }
        }
}
