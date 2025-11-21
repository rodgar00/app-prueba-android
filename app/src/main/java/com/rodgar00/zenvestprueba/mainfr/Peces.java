package com.rodgar00.zenvestprueba.mainfr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;
import com.rodgar00.zenvestprueba.R;

public class Peces extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_arrecifes, container, false);
    }

    public void alertaGuay(MaterialTextView tarea) {

        new AlertDialog.Builder(requireContext())
                .setTitle("Cambiar color")
                .setMessage("Al aceptar cambiará el color de la tarea")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tarea.setTextColor(requireContext().getColor(R.color.md_theme_primary));
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    Toast.makeText(requireContext(), "Fuera de aquí", Toast.LENGTH_SHORT).show();
                })
                .setIcon(R.drawable.cat)
                .show();
    }
}