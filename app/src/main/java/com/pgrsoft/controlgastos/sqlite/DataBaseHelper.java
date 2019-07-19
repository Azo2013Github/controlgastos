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

    public static final String MOVIMIENTOS_TABLE = "MOVIMIENTOS"; // Movimiento
    private static final String COL_1_MOVIMIENTOS = "CODIGO";
    private static final String COL_2_MOVIMIENTOS = "IMPORTE";
    private static final String COL_3_MOVIMIENTOS = "DESCRIPCION";
    private static final String COL_4_MOVIMIENTOS = "FECHA";
    private static final String COL_5_MOVIMIENTOS = "SALDO";
    private static final String COL_6_MOVIMIENTOS = "CODIGO_PRODUCTO";
    private static final String COL_7_MOVIMIENTOS = "CODIGO_CATEGORIA";

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

        String strDDL = builder.toString();
        sb.execSQL(strDDL);

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

        builder.setLength(0);
        builder.append("CREATE TABLE " + MOVIMIENTOS_TABLE + " (")
                .append(COL_1_MOVIMIENTOS).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2_MOVIMIENTOS).append(" REAL NOT NULL, ")
                .append(COL_3_MOVIMIENTOS).append(" TEXT NOT NULL, ")
                .append(COL_4_MOVIMIENTOS).append(" TEXT NOT NULL, ")
                .append(COL_5_MOVIMIENTOS).append(" REAL NOT NULL, ")
                .append(COL_6_MOVIMIENTOS).append(" INTEGER NOT NULL, ")
                .append(" FOREIGN KEY " + "( " + COL_6_MOVIMIENTOS + ") REFERENCES " + PRODUCTOS_TABLE + " (" + COL_1_PRODUCTOS + " )) ");


        strDDL = builder.toString();
        Log.d("****", strDDL);
        sb.execSQL(strDDL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTOS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIMIENTOS_TABLE);
        onCreate(db);
    }

}