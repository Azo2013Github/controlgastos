package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Es necesario solicitar una instancia de SQLiteDatabase para entrar en onCreate o onUpgrade
        sql = dataBaseHelper.getWritableDatabase();

        Categoria categoria = dataBaseHelper.createCategoria(new Categoria());

        sql.close();

        Log.d("*","FIN");

    }
}
