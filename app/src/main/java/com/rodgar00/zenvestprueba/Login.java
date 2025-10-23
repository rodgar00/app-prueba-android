package com.rodgar00.zenvestprueba;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button loginButton = findViewById(R.id.LoginButton);
        TextView loginTVRegister = findViewById(R.id.LoginTVRegister);
        TextInputLayout loginTILUser = findViewById(R.id.LoginTILuserName);
        TextInputLayout loginTILPassword = findViewById(R.id.LoginTILpassword);

        loginTILPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();

                if (password.isEmpty()) {
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("Tu contraseña está vacía");
                } else {
                    loginTILPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        loginTILUser.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (username.isEmpty()) {
                    loginTILUser.setErrorEnabled(true);
                    loginTILUser.setError("Tu nombre está vacío");
                } else {
                    loginTILUser.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(loginTILUser.getEditText().getText());
                String password = String.valueOf(loginTILPassword.getEditText().getText());

                if (username.isEmpty()){
                    Toast.makeText(Login.this, "El usuario no puede estar vacío", Toast.LENGTH_SHORT).show();
                    loginTILUser.setErrorEnabled(true);
                    loginTILUser.setError("Tu nombre está vacío");
                    return;
                }

                if (password.isEmpty()){
                    Toast.makeText(Login.this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("Tu contraseña está vacía");
                    return;
                }


                Toast toast = Toast.makeText(Login.this, "Bienvenido "+ username, Toast.LENGTH_SHORT);
                toast.show();
                Intent intentMain = new Intent(Login.this, MainActivity.class);
                startActivity(intentMain);
            }
        });

        loginTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(Login.this, Register.class);
                startActivity(intentRegister);
            }
        });
    }
}