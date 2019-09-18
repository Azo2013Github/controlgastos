package com.pgrsoft.controlgastos.adaptador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pgrsoft.controlgastos.fragment.EstadisticaFragment;
import com.pgrsoft.controlgastos.fragment.ListadoDetalleFragment;
import com.pgrsoft.controlgastos.fragment.MenuFragment;
import com.pgrsoft.controlgastos.fragment.RecyclerFragment;

public class ViewPagerAdapters extends FragmentStatePagerAdapter {


    private int numOfTap;

    public ViewPagerAdapters(FragmentManager fragmentManager, int numOfTap){

        super(fragmentManager);
        this.numOfTap = numOfTap;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                MenuFragment menuFragment = new MenuFragment();
                return menuFragment;
            case 1:
                RecyclerFragment recyclerFragment =  new RecyclerFragment();
                return recyclerFragment;
            case 2:
                EstadisticaFragment estadisticaFragment = new EstadisticaFragment();
                return estadisticaFragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTap;
    }
}
