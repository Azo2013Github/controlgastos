package com.pgrsoft.controlgastos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperBK extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "control_gastos.db"; // EL nombre de la bases de datos

    private static final String CATEGORIAS_TABLE = "CATEGORIAS"; // el nombre de la tabla categoria.
    private static final String COL1_CODIDO_CAT = "CODIGO";
    private static final String COL2_NOMBRE_CAT = "NOMBRE";

    private static final String PRODUCTOS_TABLE = "PRODUCTOS"; // tabla Productos
    private static final String COL_1_PRODUCTOS = "CODIGO";
    private static final String COL_2_PRODUCTOS = "NOMBRE";
    private static final String COL_3_PRODUCTOS = "DESCRIPCION";
    private static final String COL_4_PRODUCTOS = "PRECIO";
    private static final String COL_5_PRODUCTOS = "CODIGO_CATEGORIA";

    public static final String MOVIMIENTOS_TABLE = "MOVIMIENTOS"; // Tabla Movimiento
    private static final String COL_1_MOVIMIENTOS = "CODIGO";
    private static final String COL_2_MOVIMIENTOS = "IMPORTE";
    private static final String COL_3_MOVIMIENTOS = "DESCRIPCION";
    private static final String COL_4_MOVIMIENTOS = "FECHA";
    private static final String COL_5_MOVIMIENTOS = "SALDO";
    private static final String COL_6_MOVIMIENTOS = "CODIGO_PRODUCTO";

    public DataBaseHelperBK(Context context) {
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
                .append(" FOREIGN KEY " + "( " + COL_5_PRODUCTOS + " ) REFERENCES " + CATEGORIAS_TABLE + " (" + COL1_CODIDO_CAT + "  ))");
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

    // INSERCION DE DATOS:
    public Categoria createCategoria(Categoria categoria) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_NOMBRE_CAT, categoria.getNombre());

        long resultado = db.insert(CATEGORIAS_TABLE, null, contentValues);

        categoria.setCodigo(resultado);
        return resultado == -1 ? null : categoria;
    }

    public List<Categoria> getAll() {

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CATEGORIAS_TABLE + " ORDER BY " + COL1_CODIDO_CAT + " ASC ", null);

        List<Categoria> categorias = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                Long codigo = cursor.getLong(0);
                String nombre = cursor.getString(1);

                Categoria categoria = new Categoria(codigo, nombre);
                categorias.add(categoria);
            }

            //Log.d("***", categorias.toString());
        }
        db.close();
        return categorias;
    }

    public Categoria getCategoria(Long codigo) {

        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(codigo)};
        Categoria categoria = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + CATEGORIAS_TABLE +
                " WHERE " + COL1_CODIDO_CAT + " = ?", args);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            Long code = cursor.getLong(0);
            String nombre = cursor.getString(1);
            // RESOLVER ESTO!
            categoria = new Categoria(code, nombre);
            categoria.setCodigo(codigo);
        }

        //Log.d("CODIGO", categoria.toString());
        db.close();
        return categoria;


    }

    // Insert the new Product.
    public Producto createProducto(Producto producto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_PRODUCTOS, producto.getNombre());
        contentValues.put(COL_3_PRODUCTOS, producto.getDescripcion());
        contentValues.put(COL_4_PRODUCTOS, producto.getPrecio());
        contentValues.put(COL_5_PRODUCTOS, producto.getCategoria().getCodigo());

        long resultado = db.insert(PRODUCTOS_TABLE, null, contentValues);
        //Log.d("******", "DAR ALTA AL PRODUCTO: " + producto.toString());
        producto.setCodigo(resultado);
        db.close();
        return resultado == -1 ? null : producto;
    }

    public List<Producto> getAllProducto() {

        SQLiteDatabase db = getWritableDatabase();
        /*CategoriaServices categoriaServices = new CategoriaServicesImplBK(this.getClass().getContext);


        Cursor cursor = db.rawQuery("SELECT * FROM " + PRODUCTOS_TABLE + " ORDER BY " + COL_1_PRODUCTOS + " DESC", null);

        List<Producto> productos = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Long codigo = cursor.getLong(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                double precio = cursor.getDouble(3);
                Long codigoCategoria = cursor.getLong(4);

                Categoria categoria = categoriaServices.read(codigoCategoria);
                Producto producto = new Producto(codigo, nombre, descripcion, precio, categoria);
                producto.setCodigo(codigo);


                productos.add(producto);

            }
        }

        Log.d("Datos producto", productos.toString());
        db.close();*/
        return null; //productos;

    }

    public Producto getProducto(Long codigo) {


        Producto producto = null;

        /*SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(codigo)};

        Cursor cursor = db.rawQuery("SELECT * FROM " + PRODUCTOS_TABLE + " WHERE " + COL_1_PRODUCTOS + " = ?", args);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            Long code = cursor.getLong(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            double precio = cursor.getDouble(3);
            Long codigoCategoria = cursor.getLong(4);

            Categoria categoria = createCategoria(new Categoria());

            producto = new Producto(codigo, nombre, descripcion, precio, categoria);
            producto.setCodigo(codigo);
            categoria.setCodigo(codigoCategoria);

            Log.d("**", producto.toString());

        }*/
        return producto;

    }

    public static String getCategoriasTable() {
        return CATEGORIAS_TABLE;
    }

    public static String getCol1CodidoCat() {
        return COL1_CODIDO_CAT;
    }

    public static String getCol2NombreCat() {
        return COL2_NOMBRE_CAT;
    }

    public static String getProductosTable() {
        return PRODUCTOS_TABLE;
    }

    public static String getCol1Productos() {
        return COL_1_PRODUCTOS;
    }

    public static String getCol2Productos() {
        return COL_2_PRODUCTOS;
    }

    public static String getCol3Productos() {
        return COL_3_PRODUCTOS;
    }

    public static String getCol4Productos() {
        return COL_4_PRODUCTOS;
    }

    public static String getCol5Productos() {
        return COL_5_PRODUCTOS;
    }

    public static String getMovimientosTable() {
        return MOVIMIENTOS_TABLE;
    }

    public static String getCol1Movimientos() {
        return COL_1_MOVIMIENTOS;
    }

    public static String getCol2Movimientos() {
        return COL_2_MOVIMIENTOS;
    }

    public static String getCol3Movimientos() {
        return COL_3_MOVIMIENTOS;
    }

    public static String getCol4Movimientos() {
        return COL_4_MOVIMIENTOS;
    }

    public static String getCol5Movimientos() {
        return COL_5_MOVIMIENTOS;
    }

    public static String getCol6Movimientos() {
        return COL_6_MOVIMIENTOS;
    }
}