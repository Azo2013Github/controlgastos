package com.pgrsoft.controlgastos.fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private ImageButton botonComprar;
    private ImageButton botonPagar;
    private ImageButton botonGuardar;

    private Spinner spinner;

    private EditText editNombre;
    private EditText editPrecio;
    private EditText editDescripcion;
    private EditText editImporte;
    private EditText editFecha;
    private EditText editSaldo;
    private ImageView imageView;

    private ArrayAdapter<String> stringArrayAdapter;
    private List<Categoria> categoriaListas;
    private List<Producto> productos;
    private List<Movimiento> movimientos;

    private MovimientoServices movimientoServices;
    private ProductoServices productoServices;
    private CategoriaServices categoriaServices;

    private Categoria categoria;
    private Producto producto;
    private Movimiento movimiento;

    private final static int RESIZE_PHOTO_PIXEL_PERCENTAGE = 50; //esta variable sirve para la calidad de la imagen:
    private MagicalPermissions magicalPermissions;
    private MagicalCamera magicalCamera;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        botonComprar = (ImageButton) miVista.findViewById(R.id.idCompra);
        botonPagar = (ImageButton) miVista.findViewById(R.id.idPagar);
        botonGuardar = (ImageButton) miVista.findViewById(R.id.idGuardar);

        spinner = (Spinner) miVista.findViewById(R.id.idCategoria);
        cargarSpinner();

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        editImporte = (EditText) miVista.findViewById(R.id.idImporte);
        editFecha = (EditText) miVista.findViewById(R.id.idFecha);
        editSaldo = (EditText) miVista.findViewById(R.id.idSaldo);
        imageView = (ImageView) miVista.findViewById(R.id.idImage);

        categoriaListas = new ArrayList<>();
        productos = new ArrayList<>();
        movimientos = new ArrayList<>();

        /***********************************************/
        hacerFoto();

        botonComprar.setOnClickListener(this);
        botonPagar.setOnClickListener(this);
        botonGuardar.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        Fragment fragment;

        switch (view.getId()){

            case R.id.idCompra:

                String strCategoria = spinner.getSelectedItem().toString();
                String nombre = editNombre.getText().toString();
                double precio = Double.parseDouble(editPrecio.getText().toString());
                double importe = Double.parseDouble(editImporte.getText().toString());

                String descripcion = editDescripcion.getText().toString();
                double saldo = Double.parseDouble(editSaldo.getText().toString());

                categoria = new Categoria(1L, strCategoria);

                producto = new Producto(1L, nombre, descripcion, precio, categoria);

                movimiento = new Movimiento(1L, importe, descripcion, new Date(), saldo, producto);

                categoriaListas.add(categoria);
                productos.add(producto);
                movimientos.add(movimiento);

                Toast.makeText(this.getActivity(), "Producto en cesta", Toast.LENGTH_LONG).show();
                editSaldo.setText("");
                editFecha.setText("");
                editDescripcion.setText("");
                editImporte.setText("");
                editPrecio.setText("");
                editNombre.setText("");

                Log.d("***", "Valor categoria: "+categoriaListas.size());
                Log.d("***", "Valor de productos: " +productos.size());
                Log.d("***", "Categoria: " +categoria.getNombre());
                Log.d("***", "Producto: " + producto.getNombre());
                Log.d("***", "Movimiento: " +movimiento.getSaldo());

                break;

            case R.id.idPagar:
                Log.d("***", "PAGAR");
                //categoriaListas
                categoriaServices = new CategoriaServicesImpl(this.getActivity());

                productoServices = new ProductoServicesImpl(this.getActivity());

                movimientoServices = new MovimientoServicesImpl(this.getActivity());

                for (int i=0; i< categoriaListas.size(); i++) {

                    categoriaServices.create(categoriaListas.get(i));

                    productoServices.create(productos.get(i));

                    movimientoServices.create(movimientos.get(i));
                }

                break;
            case R.id.idGuardar:
                magicalCamera.takePhoto();
                fragment = new ListadoDetalleFragment();
                //fragment.getActivity().startActivities();

                break;
        }

    }


    private void hacerFoto(){
        /* ****************************
        Esta parte sirve para hacer foto y con el boton botonGuardar guardamos las imagenes que hemos echo: */
        String [] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        magicalPermissions = new MagicalPermissions(this,permissions);
        magicalCamera = new MagicalCamera(this.getActivity(), RESIZE_PHOTO_PIXEL_PERCENTAGE, magicalPermissions);
    }


    /* Funciones a implementar para usar la camara son:
    * 1) */

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            magicalCamera.resultPhoto(requestCode, resultCode, data);

            imageView.setImageBitmap(magicalCamera.getPhoto());

            String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),
                          "myPhotoName",
                        "myDirectoryName",
                                      magicalCamera.JPEG,
                    true);


        }

       /* if (requestCode == 1 && resultCode == RESULT_OK){ // RESULT_OK devuelve -1 si las cosas han ido bien

            Bundle extras = data.getExtras(); // Ya tengo referencia al Bundle a partir de Intent
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // Lo de "data" hay que saberlo..
            imageView.setImageBitmap(imageBitmap);

            // Como posiblemente guarde este bitmap en el sistema de archivos
            // me interesara tb guardar el bitmap en la variable de instancia de esta Actvity
            imageActual = imageBitmap;

        }*/

    }

    private void cargarSpinner(){

        List<Categoria> categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria(1L, "CARNE");
        Categoria categoria2 = new Categoria(2L, "VERDURA");
        Categoria categoria3 = new Categoria(3L, "SUMINISTROS");
        Categoria categoria4 = new Categoria(4L, "LEGUMBRE");
        Categoria categoria5 = new Categoria(5L, "BEBIDAS");
        Categoria categoria6 = new Categoria(6L, "ROPA");
        Categoria categoria8 = new Categoria(8L, "ALQUILER");
        Categoria categoria7 = new Categoria(7L, "EXTRAS");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);

        int i = 0;
        String [] strNombres = new String [8];

        for (Categoria categoria: categorias) {
            strNombres [i] = categoria.getNombre(); //cogemos los nombres de los agentes en cada posicion
            Toast.makeText(getActivity(), strNombres[i], Toast.LENGTH_LONG).show();
            i++; //Incrementamos el i para que salga todos los nombres de los agentes
        }

        stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, strNombres);

        spinner.setAdapter(stringArrayAdapter);

    }

}
