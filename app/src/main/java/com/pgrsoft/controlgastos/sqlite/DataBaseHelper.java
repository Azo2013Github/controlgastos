package com.pgrsoft.controlgastos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "control_gastos.db"; // EL nombre de la bases de datos

    private static final String CATEGORIAS_TABLE = "CATEGORIAS"; // el nombre de la tabla categoria.
    private static final String COL1_CODIDO_CAT = "CODIGO";
    private static final String COL2_NOMBRE_CAT = "NOMBRE";

    private static final String PRODUCTOS_TABLE = "PRODUCTOS"; // tabla Productos
    private static final String COL_1_PRODUCTOS = "CODIGO";
    private static final String COL_2_PRODUCTOS = "NOMBRE";
    private static final String COL_3_PRODUCTOS = "DESCRIPCION";
    private static final String COL_4_PRODUCTOS = "PRECIO";
    private static final String COL_5_PRODUCTOS = "IMAGEN";
    private static final String COL_6_PRODUCTOS = "CODIGO_CATEGORIA";

    public static final String MOVIMIENTOS_TABLE = "MOVIMIENTOS"; // Tabla Movimiento
    private static final String COL_1_MOVIMIENTOS = "CODIGO";
    private static final String COL_2_MOVIMIENTOS = "IMPORTE";
    private static final String COL_3_MOVIMIENTOS = "DESCRIPCION";
    private static final String COL_4_MOVIMIENTOS = "FECHA";
    private static final String COL_6_MOVIMIENTOS = "CODIGO_PRODUCTO";


    public DataBaseHelper(Context context){

        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuilder builder = new StringBuilder();

        // Creación de tablas
        builder.append("CREATE TABLE " + CATEGORIAS_TABLE + " (")
                .append(COL1_CODIDO_CAT).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL2_NOMBRE_CAT).append(" TEXT NOT NULL )");

        String strDDL = builder.toString();
        sqLiteDatabase.execSQL(strDDL);

        builder.setLength(0);
        builder.append("CREATE TABLE " + PRODUCTOS_TABLE + " (")
                .append(COL_1_PRODUCTOS).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2_PRODUCTOS).append(" TEXT NOT NULL, ")
                .append(COL_3_PRODUCTOS).append(" TEXT NOT NULL, ")
                .append(COL_4_PRODUCTOS).append(" REAL NOT NULL, ")
                .append(COL_5_PRODUCTOS).append(" INTEGER NOT NULL, ")
                .append(COL_6_PRODUCTOS).append(" INTEGER NOT NULL, ")
                .append(" FOREIGN KEY " + "( " + COL_6_PRODUCTOS + " ) REFERENCES " + CATEGORIAS_TABLE + " (" + COL1_CODIDO_CAT + "  ))");
        strDDL = builder.toString();
        sqLiteDatabase.execSQL(strDDL);

        builder.setLength(0);
        builder.append("CREATE TABLE " + MOVIMIENTOS_TABLE + " (")
                .append(COL_1_MOVIMIENTOS).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2_MOVIMIENTOS).append(" REAL NOT NULL, ")
                .append(COL_3_MOVIMIENTOS).append(" TEXT NOT NULL, ")
                .append(COL_4_MOVIMIENTOS).append(" TEXT NOT NULL, ")
                .append(COL_6_MOVIMIENTOS).append(" INTEGER NOT NULL, ")
                .append(" FOREIGN KEY " + "( " + COL_6_MOVIMIENTOS + ") REFERENCES " + PRODUCTOS_TABLE + " (" + COL_1_PRODUCTOS + " )) ");

        strDDL = builder.toString();

        sqLiteDatabase.execSQL(strDDL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCTOS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIMIENTOS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Categoria createCategoria(Categoria categoria){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        long resultado = db.insert(CATEGORIAS_TABLE, null, contentValues);

        categoria.setCodigo(resultado);

        db.close();
        return resultado == -1 ? null : categoria;
    }

    public Cursor getCategoria(Long codigo) {

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(codigo)};

        /* Cursor cursor = db.rawQuery("SELECT * FROM " + CATEGORIAS_TABLE +
                " WHERE " + COL1_CODIDO_CAT + " = ?", args);*/
        return  db.rawQuery("SELECT * FROM " + CATEGORIAS_TABLE +
                " WHERE " + COL1_CODIDO_CAT + " = ?", args);

    }

       // Las partes de la tabla Productos:
    public Producto createProducto(Producto producto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_PRODUCTOS, producto.getNombre());
        contentValues.put(COL_3_PRODUCTOS, producto.getDescripcion());
        contentValues.put(COL_4_PRODUCTOS, producto.getPrecio());
        contentValues.put(COL_5_PRODUCTOS, producto.getImagen());
        contentValues.put(COL_6_PRODUCTOS, producto.getCategoria().getCodigo());

        long resultado = db.insert(PRODUCTOS_TABLE, null, contentValues);
        producto.setCodigo(resultado);

        db.close();
        return resultado == -1 ? null : producto;

    }

    public Cursor getAllCategoriesCursor(){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + CATEGORIAS_TABLE + " ORDER BY " + COL1_CODIDO_CAT + " ASC ", null);

    }

    public Categoria updateCategoria(Categoria categoria){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        String[] args = new String[] {String.valueOf(categoria.getCodigo())};

        db.update(CATEGORIAS_TABLE, contentValues,  COL1_CODIDO_CAT + " = ? ",args);

        db.close();
        return categoria;

    }

    public Cursor getAllProductosCursor(){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + PRODUCTOS_TABLE +
                " ORDER BY " + COL_1_PRODUCTOS + " DESC ", null);

    }

    public Cursor getProducto(Long codigo){

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(codigo)};

        return db.rawQuery("SELECT * FROM " + PRODUCTOS_TABLE + " WHERE " + COL_1_PRODUCTOS + " = ?", args);

    }

    // La parte de Movimiento: .....
    public Movimiento createMovimiento(Movimiento movimiento){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_MOVIMIENTOS, movimiento.getImporte());
        contentValues.put(COL_3_MOVIMIENTOS, movimiento.getDescripcion());
        contentValues.put(COL_4_MOVIMIENTOS, getMillisecondsFromDate(movimiento.getFecha()));
        contentValues.put(COL_6_MOVIMIENTOS, movimiento.getProducto().getCodigo());

        long resultado = db.insert(MOVIMIENTOS_TABLE, null, contentValues);
        movimiento.setCodigo(resultado);

        db.close();
        return resultado == -1 ? null : movimiento;
    }

    public Cursor getAllMovimientosCursor(){

        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + MOVIMIENTOS_TABLE +
                " ORDER BY " + COL_1_MOVIMIENTOS + " DESC", null);

    }

    public Cursor getMovimiento(Long codigo){

        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(codigo)};

        return db.rawQuery("SELECT * FROM " + MOVIMIENTOS_TABLE +
                " WHERE " + COL_1_MOVIMIENTOS + " = ?", args);
    }

    /* ESTE PARTE ES PARA HACER UNA QUERY SOBRE LA FECHA: */
    public Cursor getDateBetweenQuery(Date dateInicial, Date dateFinal){

        SQLiteDatabase db = getWritableDatabase();
        if (dateInicial == null || dateFinal == null){
            Log.d("***", "OJO ALGUNA FECHA ES NULL!!!!");
        }
        String consulta = "SELECT " + COL_1_MOVIMIENTOS + " , "
                +COL_2_MOVIMIENTOS + " , " + COL_3_MOVIMIENTOS + " , " + COL_4_MOVIMIENTOS + " ,"  + COL_6_MOVIMIENTOS+
                " from " + MOVIMIENTOS_TABLE + " where "
                + COL_4_MOVIMIENTOS + " >= " + getMillisecondsFromDate(dateInicial) + " AND " + COL_4_MOVIMIENTOS + " <= " + getMillisecondsFromDate(dateFinal) + " ORDER BY " +
                    COL_2_MOVIMIENTOS + " DESC ";

        Cursor cursor = db.rawQuery(consulta, null);

        return cursor;
    }

    private String getMillisecondsFromDate(Date date){
        return String.valueOf(date.getTime());
    }

    public Producto updatingProducto (Producto producto){

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(producto.getCodigo())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_PRODUCTOS, producto.getNombre());
        contentValues.put(COL_3_PRODUCTOS, producto.getDescripcion());
        contentValues.put(COL_4_PRODUCTOS, producto.getPrecio());
        contentValues.put(COL_5_PRODUCTOS, producto.getImagen());
        contentValues.put(COL_6_PRODUCTOS, producto.getCategoria().getCodigo());

        db.update(PRODUCTOS_TABLE, contentValues, COL_1_PRODUCTOS + " = ?" , args);

        db.close();
        return producto;

    }

    public Movimiento updatingMovimiento(Movimiento movimiento){

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(movimiento.getCodigo())};

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_MOVIMIENTOS, movimiento.getImporte());
        contentValues.put(COL_3_MOVIMIENTOS, movimiento.getDescripcion());
        contentValues.put(COL_4_MOVIMIENTOS, getMillisecondsFromDate(movimiento.getFecha()));
        contentValues.put(COL_6_MOVIMIENTOS, movimiento.getProducto().getCodigo());

        int update = db.update(MOVIMIENTOS_TABLE, contentValues, COL_1_MOVIMIENTOS + " = ? " , args);
        Log.d("***", "Updating values: " + update);

        db.close();
        return movimiento;

    }

    public boolean deletingProductoCodigo(Long codigo){

        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(codigo)};

        db.delete(PRODUCTOS_TABLE, COL_1_PRODUCTOS + " = ? ", args);
        db.close();
        return true;
    }

    public boolean deletingMovimientoCodigo(Long codigo){

        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(codigo)};
        int consulta =  db.delete(MOVIMIENTOS_TABLE, COL_1_MOVIMIENTOS + " = ? ", args);
        Log.d("***", "deleting codigo: " + consulta);
        db.close();
        return true;
    }

    public boolean deletingCategoria(Long codigo) {

        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(codigo)};
        db.delete(CATEGORIAS_TABLE, COL1_CODIDO_CAT + " = ? ", args);
        db.close();
        return true;
    }




}
