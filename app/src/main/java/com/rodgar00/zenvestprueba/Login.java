package com.rodgar00.zenvestprueba;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

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
        TextInputLayout loginTIL = findViewById(R.id.LoginTILuserName);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(loginTIL.getEditText().getText());
                Toast toast = Toast.makeText(Login.this, username, Toast.LENGTH_SHORT);
                toast.show();
                Intent intentMain = new Intent(Login.this, MainActivity.class);
                startActivity(intentMain);
            }
        });

        /**
         * TAREA: HACER UNA ACTIVIDAD PARA REGISTRARSE Y PODER LLEGAR A ELLA DESDE EL loginTVRegister
         */

    }
}