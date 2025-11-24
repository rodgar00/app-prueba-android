package com.rodgar00.zenvestprueba.mainfr;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rodgar00.zenvestprueba.R;

public class GatoNoMolon extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nomolones, container, false);

        int[] tarjetasIds = {
                R.id.tarjeta_gatos_1,
                R.id.tarjeta_gatos_2,
                R.id.tarjeta_gatos_3,
                R.id.tarjeta_gatos_4,
                R.id.tarjeta_gatos_5,
                R.id.tarjeta_gatos_6
        };

        for (int id : tarjetasIds) {

            View tarjeta = view.findViewById(id);

            tarjeta.setOnClickListener(v -> alertaGuay(tarjeta));
        }

        return view;
    }

    private void alertaGuay(View tarjeta) {

        new AlertDialog.Builder(requireContext())
                .setTitle("Cambiar color")
                .setMessage("¿Quieres cambiar el color de esta tarjeta?")
                .setPositiveButton("Aceptar", (dialog, which) -> {

                    tarjeta.setBackgroundColor(requireContext().getColor(R.color.md_theme_primary));

                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                })
                .setIcon(R.drawable.cat)
                .show();
    }
}
