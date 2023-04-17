package com.example.mydailys.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydailys.MainActivity;
import com.example.mydailys.R;
import com.example.mydailys.util.Utils;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageButton login_button;
    private TextView signup_text;
    private CheckBox remember_me;
    boolean isValid = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_edittext);
        password = findViewById(R.id.password_edittext);
        login_button = findViewById(R.id.login_button);
        signup_text = findViewById(R.id.signup_text);
        remember_me = findViewById(R.id.remember_checkBox);

        sharedPreferences = getApplicationContext().getSharedPreferences("UserDatabase", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if (sharedPreferences != null) {
            String savedUsername = sharedPreferences.getString("Username", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            RegistrationActivity.user = new User(savedUsername, savedPassword);

            if (sharedPreferences.getBoolean("RememberMeCheckbox", false)) {
                username.setText(savedUsername);
                password.setText(savedPassword);
                remember_me.setChecked(true);
            }
        }

        remember_me.setOnClickListener(view -> {
            sharedPreferencesEditor.putBoolean("RememberMeCheckbox", remember_me.isChecked());
            sharedPreferencesEditor.apply();
        });

        login_button.setOnClickListener(view -> {

            String inputName = username.getText().toString();
            String inputPassword = password.getText().toString();

            if (inputName.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Username and Password cannot be empty!",
                        Toast.LENGTH_LONG).show();
            } else {
                isValid = validate(inputName, inputPassword);

                if (!isValid) {
                    Toast.makeText(this, "Incorrect credentials! Please retry.",
                            Toast.LENGTH_LONG).show();
                }
            } if (isValid){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("user_id", userId);
                startActivity(intent);
                Toast.makeText(this, "Successfully logged-in!",
                        Toast.LENGTH_LONG).show();
            }
            Utils.hideKeyboard(view);
        });

        signup_text.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private boolean validate(String name, String password) {
        if (RegistrationActivity.user != null) {
            return name.equals(RegistrationActivity.user.getUsername()) &&
                    password.equals(RegistrationActivity.user.getPassword());
        }
        return false;
    }
}