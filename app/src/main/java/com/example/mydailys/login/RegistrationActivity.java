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

public class RegistrationActivity extends AppCompatActivity {

    private EditText regUsername;
    private EditText regPassword;
    private ImageButton regLoginButton;
    public static User user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regUsername = findViewById(R.id.register_username_edittext);
        regPassword = findViewById(R.id.register_password_edittext);
        regLoginButton = findViewById(R.id.register_login_button);

        sharedPreferences = getApplication().getSharedPreferences("UserDatabase", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        regLoginButton.setOnClickListener(view -> {
            String registerUsername = regUsername.getText().toString();
            String registerPassword = regPassword.getText().toString();

            if (validate(registerUsername, registerPassword)) {
                user = new User(registerUsername, registerPassword);

                // store user data after passing validation
                sharedPreferencesEditor.putString("Username", registerUsername);
                sharedPreferencesEditor.putString("Password", registerPassword);

                sharedPreferencesEditor.apply();


                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                Toast.makeText(this, "Successfully registered!\n\t\t\t\t" +
                        "Please login!", Toast.LENGTH_LONG).show();
            }
            Utils.hideKeyboard(view);
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