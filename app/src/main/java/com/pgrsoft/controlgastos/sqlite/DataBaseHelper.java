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

    private static final String PRODUCTOS_TABLE = "PRODUCTOS"; // Productos
    private static final String COL_1_PRODUCTOS = "CODIGO";
    private static final String COL_2_PRODUCTOS = "NOMBRE";
    private static final String COL_3_PRODUCTOS = "DESCRIPCION";
    private static final String COL_4_PRODUCTOS = "PRECIO";
    private static final String COL_5_PRODUCTOS = "CODIGO_CATEGORIA";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sb) {

        StringBuilder builder = new StringBuilder();

        // Creación de tablas

        builder.append("CREATE TABLE " + CATEGORIAS_TABLE + " (")
                .append(COL1_CODIDO_CAT).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL2_NOMBRE_CAT).append(" TEXT NOT NULL )");
        Log.d("****", builder.toString());
        String strDDL = builder.toString();
        sb.execSQL(strDDL);

        // Creacion de la tabla de productos

        builder.setLength(0);
        builder.append("CREATE TABLE " + PRODUCTOS_TABLE + " (")
                .append(COL_1_PRODUCTOS).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2_PRODUCTOS).append(" TEXT NOT NULL, ")
                .append(COL_3_PRODUCTOS).append(" TEXT NOT NULL, ")
                .append(COL_4_PRODUCTOS).append(" REAL NOT NULL, ")
                .append(COL_5_PRODUCTOS).append(" INTEGER NOT NULL, ")
                .append(" FOREIGN KEY " + "( " + COL_5_PRODUCTOS + " ) REFERENCES " + CATEGORIAS_TABLE + " (" + COL1_CODIDO_CAT + "  ))" );
        strDDL = builder.toString();
        sb.execSQL(strDDL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("***", "ENTRA....");
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTOS_TABLE);
        onCreate(db);
    }




    public Categoria createCategoria(Categoria categoria) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        Long resultado = db.insert(CATEGORIAS_TABLE, null, contentValues);
        categoria.setCodigo(resultado);

        //Log.d("****", "DAR DE ALTA: " +categoria.getNombre());

        return categoria;
    }


}