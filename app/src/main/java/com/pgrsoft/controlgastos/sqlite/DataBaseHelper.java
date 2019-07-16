package com.pgrsoft.controlgastos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Categoria;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gastos.db"; // EL nombre de la bases de datos

    private static final String CATEGORIAS_TABLE = "categorias"; // el nombre de la tabla categoria.
    private static final String COL1_CODIDO_CAT = "codigo";
    private static final String COL2_NOMBRE_CAT = "nombre";

    private static final String PRODUCTOS_TABLE = "productos"; // Productos

    public static final String MOVIMIENTOS_TABLE = "movimientos"; // Movimiento
/*
    private static final String COL_1_PRODUCTOS = "codigo";
    private static final String COL_2_PRODUCTOS = "nombre";
    private static final String COL_3_PRODUCTOS = "descripcion";
    private static final String COL_4_PRODUCTOS = "precio";
    private static final String COL_5_PRODUCTOS = "categoria";

    private static final String COL_1_MOVIMIENTOS = "codigo";
    private static final String COL_2_MOVIMIENTOS = "importe";
    private static final String COL_3_MOVIMIENTOS = "descripcion";
    private static final String COL_4_MOVIMIENTOS = "fecha";
    private static final String COL_5_MOVIMIENTOS = "saldo";
    private static final String COL_6_MOVIMIENTOS = "producto";*/

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + CATEGORIAS_TABLE + " (")
                .append(COL1_CODIDO_CAT).append(" BIGINT PRIMARY KEY AUTOINCREMENT, ")
                .append(COL2_NOMBRE_CAT).append(" TEXT NOT NULL )");

       String strDDL = db.toString();
       db.execSQL(strDDL);

        // Creacion de la tabla de productos
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE);
        onCreate(db);
    }

    public Categoria createCategoria(Categoria categoria){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        Long resultado = db.insert(CATEGORIAS_TABLE, null, contentValues);
        categoria.setCodigo(resultado);

        Log.d("****", "DAR DE ALTA: " +categoria.getNombre());

        return categoria;
    }

}