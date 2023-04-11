package com.example.mydailys.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mydailys.MainActivity;
import com.example.mydailys.R;
import com.example.mydailys.data.TaskDao;
import com.example.mydailys.util.TaskRoomDatabase;
import com.example.mydailys.util.Utils;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText regUsername;
    private EditText regPassword;
    private ImageButton regLoginButton;
    public User user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regUsername = findViewById(R.id.register_username_edittext);
        regPassword = findViewById(R.id.register_password_edittext);
        regLoginButton = findViewById(R.id.register_login_button);

        user = new User();

        sharedPreferences = getApplication().getSharedPreferences("UserDatabase", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if (sharedPreferences != null) {

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if (preferencesMap.size() != 0) {
                user.loadUser(preferencesMap);
            }
        }

        regLoginButton.setOnClickListener(view -> {
            String registerUsername = regUsername.getText().toString();
            String registerPassword = regPassword.getText().toString();

            if (validate(registerUsername, registerPassword)) {

                if (user.checkUsername(registerUsername)) {
                    Toast.makeText(this, "Username already taken!", Toast.LENGTH_LONG).show();
                } else {
                    user.addUser(registerUsername, registerPassword);

                    // store user data after passing validation
                    sharedPreferencesEditor.putString(registerUsername, registerPassword);

                    sharedPreferencesEditor.putString("LastSavedUsername", registerUsername);
                    sharedPreferencesEditor.putString("LastSavedPassword", registerPassword);

                    sharedPreferencesEditor.apply();


                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    Toast.makeText(this, "Successfully registered!\n\t\t\t\t" +
                            "Please login!", Toast.LENGTH_LONG).show();
                }
                Utils.hideKeyboard(view);
            }
        });
    }

    private boolean validate(String name, String password) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(this, "Password should be at least 8 characters!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}