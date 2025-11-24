package com.rodgar00.zenvestprueba;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.widget.Button;
import android.widget.TextView;

import com.rodgar00.zenvestprueba.databinding.ActivityMainBinding;
import com.rodgar00.zenvestprueba.ui.frmanager.Paginador;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int contador = 0;
    private TextView mainTV;
    private ViewPager viewPager;
    private Paginador paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainTV = findViewById(R.id.mainTV);
        Button mainButton = findViewById(R.id.mainButton);
        viewPager = findViewById(R.id.coralViewPager);

        String nombre = getIntent().getStringExtra("nombre");

        if (nombre != null) {
            mainTV.setText("Hola, " + nombre);
        } else {
            mainTV.setText(String.valueOf(contador));
        }
        mainButton.setOnClickListener(v -> mainTV.setText(String.valueOf(++contador)));
        paginador = new Paginador(this, getSupportFragmentManager());
        viewPager.setAdapter(paginador);
    }
}
