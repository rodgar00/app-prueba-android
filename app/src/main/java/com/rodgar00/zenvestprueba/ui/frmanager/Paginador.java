package com.rodgar00.zenvestprueba.ui.frmanager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rodgar00.zenvestprueba.MainActivity;
import com.rodgar00.zenvestprueba.mainfr.GatoMolon;
import com.rodgar00.zenvestprueba.mainfr.GatoNoMolon;

public class Paginador extends FragmentPagerAdapter {

    public Paginador(MainActivity context, FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new GatoMolon() : new GatoNoMolon();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
