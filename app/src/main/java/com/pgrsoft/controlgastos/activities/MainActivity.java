package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Spinner;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //categoria =

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Categoria categoria = dataBaseHelper.createCategoria(new Categoria());



    }
}
