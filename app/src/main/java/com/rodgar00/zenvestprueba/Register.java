package com.rodgar00.zenvestprueba;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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

public class Register extends AppCompatActivity {

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
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout usernameTIL = findViewById(R.id.RegisterTILuserName);
        TextInputLayout emailTIL = findViewById(R.id.RegisterTILEmail);
        TextInputLayout passwordTIL = findViewById(R.id.RegisterTILPassword);
        TextInputLayout confirmPasswordTIL = findViewById(R.id.RegisterTILPasswordConfirm);
        Button registerButton = findViewById(R.id.RegisterButton);
        TextView registerTVLogin = findViewById(R.id.RegisterTVLogin);


        usernameTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (username.isEmpty()) {
                    usernameTIL.setErrorEnabled(true);
                    usernameTIL.setError("Tu nombre está vacío");
                } else {
                    usernameTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        emailTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (!validate(email)) {
                    emailTIL.setErrorEnabled(true);
                    emailTIL.setError("Tu email es inválido");
                } else {
                    emailTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        passwordTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();

                if (password.isEmpty()) {
                    passwordTIL.setErrorEnabled(true);
                    passwordTIL.setError("Tu contraseña está vacía");
                } else {
                    passwordTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(usernameTIL.getEditText().getText());
                String email = String.valueOf(emailTIL.getEditText().getText());
                String password = String.valueOf(passwordTIL.getEditText().getText());
                String confirmPassword = String.valueOf(confirmPasswordTIL.getEditText().getText());

                if (username.isEmpty()){
                    Toast.makeText(Register.this, "El usuario no puede estar vacío", Toast.LENGTH_SHORT).show();
                    usernameTIL.setErrorEnabled(true);
                    usernameTIL.setError("Tu nombre está vacío");
                    return;
                }

                if (!validate(email)) {
                    Toast.makeText(Register.this, "El email es inválido", Toast.LENGTH_SHORT).show();
                    emailTIL.setErrorEnabled(true);
                    emailTIL.setError("Tu email es inválido");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    passwordTIL.setErrorEnabled(true);
                    passwordTIL.setError("No coinciden las contraseñas");
                    return;
                }
                if (password.isEmpty()){
                    Toast.makeText(Register.this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                    passwordTIL.setErrorEnabled(true);
                    passwordTIL.setError("Tu contraseña está vacía");
                    return;
                }

                Toast.makeText(Register.this, "Registro exitoso " + username, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        registerTVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
