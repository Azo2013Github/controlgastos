package com.pgrsoft.controlgastos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Categoria;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "control_gastos.db"; // EL nombre de la bases de datos

    private static final String CATEGORIAS_TABLE = "CATEGORIAS"; // el nombre de la tabla categoria.
    private static final String COL1_CODIDO_CAT = "CODIGO";
    private static final String COL2_NOMBRE_CAT = "NOMBRE";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder builder = new StringBuilder();

        // Creación de tablas

        builder.append("CREATE TABLE " + CATEGORIAS_TABLE + " (")
               .append(COL1_CODIDO_CAT).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
               .append(COL2_NOMBRE_CAT).append(" TEXT NOT NULL )");
        Log.d("****",builder.toString());
        String strDDL = builder.toString();
        db.execSQL(strDDL);

        // Inserción de datos de ejemplo

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("***", "ENTRA....");
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE);
        onCreate(db);
    }


    public Categoria createCategoria(Categoria categoria){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        Long resultado = db.insert(CATEGORIAS_TABLE, null, contentValues);
        categoria.setCodigo(resultado);

        //Log.d("****", "DAR DE ALTA: " +categoria.getNombre());

        return categoria;
    }



}