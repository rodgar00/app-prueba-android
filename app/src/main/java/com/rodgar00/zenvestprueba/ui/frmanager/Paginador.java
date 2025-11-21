package com.rodgar00.zenvestprueba.ui.frmanager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rodgar00.zenvestprueba.MainActivity;
import com.rodgar00.zenvestprueba.mainfr.Arrecifes;
import com.rodgar00.zenvestprueba.mainfr.Peces;

public class Paginador extends FragmentPagerAdapter {

    public Paginador(MainActivity context, FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new Arrecifes() : new Peces();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
