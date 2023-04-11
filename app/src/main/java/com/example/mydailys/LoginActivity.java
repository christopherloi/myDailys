package com.example.mydailys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageButton login_button;

    private final String validateName = "admin";
    private final String validatePassword = "12345678";

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_edittext);
        password = findViewById(R.id.password_edittext);
        login_button = findViewById(R.id.login_button);

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
                startActivity(intent);
                Toast.makeText(this, "Successfully logged-in!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validate(String name, String password) {
        return name.equals(validateName) && password.equals(validatePassword);
    }
}