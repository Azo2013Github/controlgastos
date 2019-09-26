package com.pgrsoft.controlgastos.adaptador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pgrsoft.controlgastos.fragment.EstadisticaFragment;
import com.pgrsoft.controlgastos.fragment.FormularioFragment;
import com.pgrsoft.controlgastos.fragment.ListadoDetalleFragment;
import com.pgrsoft.controlgastos.fragment.MenuFragment;
import com.pgrsoft.controlgastos.fragment.RecyclerFragment;
import com.pgrsoft.controlgastos.fragment.UpdateFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapters extends FragmentStatePagerAdapter {

    private int numOfTap;
    private List<Fragment> fragments;

    public ViewPagerAdapters(FragmentManager fragmentManager){

        super(fragmentManager);
        fragments = new ArrayList<>();
        fragments.add(new MenuFragment());
        fragments.add(new RecyclerFragment());
        fragments.add(new EstadisticaFragment());
        fragments.add(new FormularioFragment());
        fragments.add(new UpdateFragment());
        //this.numOfTap = numOfTap;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        //return numOfTap;
        return fragments.size();
    }
}
