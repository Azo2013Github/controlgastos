package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ViewPagerAdapters;
import com.pgrsoft.controlgastos.fragment.FormularioFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.idtabLayout);
        toolbar = (Toolbar) findViewById(R.id.idToolbar);
        viewPager = (ViewPager) findViewById(R.id.idViewPager);

        setSupportActionBar(toolbar); // PAra el action bar

        tabLayout.addTab(tabLayout.newTab().setText("MENU"));
        tabLayout.addTab(tabLayout.newTab().setText("LIST"));
        tabLayout.addTab(tabLayout.newTab().setText("ESTATISTIC"));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        ViewPagerAdapters viewPagerAdapters = new ViewPagerAdapters(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapters);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        /*MenuFragment menuFragment = new MenuFragment();

        //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.destino, menuFragment);

        fragmentTransaction.addToBackStack(null); // que hace?

        fragmentTransaction.commit();*/

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:


                break;
            case R.id.action_add:
                FormularioFragment formularioFragment = new FormularioFragment();

                //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.idViewPager, formularioFragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
                break;
            case R.id.action_help:
                Toast.makeText(this, "APP CONTROL GASTOS \n: https://github.com/Azo2013Github/controlgastos", Toast.LENGTH_LONG).show();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
