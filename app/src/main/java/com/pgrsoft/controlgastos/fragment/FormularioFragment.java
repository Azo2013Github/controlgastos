package com.pgrsoft.controlgastos.fragment;


//import android.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

//import com.frosquivel.magicalcamera.MagicalCamera;
//import com.frosquivel.magicalcamera.MagicalPermissions;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private Button btnAdd;
    private Button btnList;

    private Spinner spinner;

    private EditText editNombre;
    private EditText editPrecio;
    private EditText editDescripcion;
    private EditText editDesMovimiento;
    private EditText editCantidad;
    private ImageView imageView;

    private ArrayAdapter<String> stringArrayAdapter;
    private List<Categoria> categorias;

    private MovimientoServices movimientoServices;
    private ProductoServices productoServices;
    private CategoriaServices categoriaServices;

    private Categoria categoria;
    private Producto producto;
    private Movimiento movimiento;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        btnAdd = (Button) miVista.findViewById(R.id.idBtnAdd);
        btnList = (Button) miVista.findViewById(R.id.idBtnList);

        spinner = (Spinner) miVista.findViewById(R.id.idSpinnerCategory);
        cargarSpinner();

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        editCantidad = (EditText) miVista.findViewById(R.id.idCantidad);
        editDesMovimiento = (EditText) miVista.findViewById(R.id.idDesMovimiento);
        imageView = (ImageView) miVista.findViewById(R.id.idImage);

        categoriaServices = new CategoriaServicesImpl(this.getActivity());
        productoServices = new ProductoServicesImpl(this.getActivity());
        movimientoServices = new MovimientoServicesImpl(this.getActivity());

        btnAdd.setOnClickListener(this);
        btnList.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            /*case R.id.idBtnAdd:
                if (spinner.toString().equals("") || editCantidad.getText().toString().equals("") || editPrecio.getText().toString().equals("") ||
                    editDesMovimiento.getText().toString().equals("") || editNombre.getText().toString().equals("")
                    || editDescripcion.getText().toString().equals("")){
                    Log.d("***", "The EditTexts are Empty ");
                }else{
                    String strCategoria = spinner.getSelectedItem().toString();
                    String nombre = editNombre.getText().toString();
                    double precio = Double.parseDouble(editPrecio.getText().toString());
                    double importe = Double.parseDouble(editCantidad.getText().toString()) * Double.parseDouble(editPrecio.getText().toString());

                    String descripcion = editDescripcion.getText().toString();
                    String desMovimiento = editDesMovimiento.getText().toString();

                    categoria = new Categoria(strCategoria);
                    int image = changeImageListView(categoria.getNombre()); //Guardar las categorias  // en funcion de las Imagenes para añadir en la BBDD.

                    producto = new Producto(nombre, descripcion, precio, image, categoria);
                    movimiento = new Movimiento(importe, desMovimiento, new Date(), producto);

                    categoriaServices.create(categoria);
                    productoServices.create(producto);
                    movimientoServices.create(movimiento);
                }
                vaciarEditText();

                break;

            case R.id.idBtnList:

                /*GastoListadoFragment fragment = new GastoListadoFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;*/

        }

    }

    // convert from bitmap to byte array para guardar las imagen en la bbdd
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    private void cargarSpinner(){

        categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria("CARNE");
        Categoria categoria2 = new Categoria("VERDURA");
        Categoria categoria3 = new Categoria("SUMINISTROS");
        Categoria categoria4 = new Categoria("LEGUMBRE");
        Categoria categoria5 = new Categoria("BEBIDAS");
        Categoria categoria6 = new Categoria("ROPA");
        Categoria categoria7 = new Categoria("ALQUILER");
        Categoria categoria8 = new Categoria("EXTRAS");
        Categoria categoria9 = new Categoria("FARMACIA");
        Categoria categoria10 = new Categoria("ZAPATOS");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);
        categorias.add(categoria9);
        categorias.add(categoria10);
        int i = 0;
        String [] strNombres = new String [10];

        for (Categoria categoria: categorias) {
            strNombres [i] = categoria.getNombre(); //cogemos los nombres de los agentes en cada posicion
            i++; //Incrementamos el i para que salga todos los nombres de los agentes
        }
        stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, strNombres);
        spinner.setAdapter(stringArrayAdapter);

    }

    /* Esta funcion sirve qua que las imagenes vayan cambiando segun categoria */
    private int changeImageListView(String nombre){

        int valorImage = 0;

        switch (nombre){

            case "CARNE":
                imageView.setImageResource(R.drawable.carne);
                valorImage = 1;
                break;
            case "VERDURA":
                imageView.setImageResource(R.drawable.verduras);
                valorImage = 2;
                break;
            case"SUMINISTROS":
                imageView.setImageResource(R.drawable.grifo);
                valorImage = 3;
                break;
            case"LEGUMBRE":
                imageView.setImageResource(R.drawable.legumbres);
                valorImage = 4;
                break;
            case"BEBIDAS":
                imageView.setImageResource(R.drawable.bebidas);
                valorImage = 5;
                break;
            case"ROPA":
                imageView.setImageResource(R.drawable.ropa);
                valorImage = 6;
                break;

            case"ALQUILER":
                imageView.setImageResource(R.drawable.casa);
                valorImage = 7;
                break;
            case"EXTRAS":
                imageView.setImageResource(R.drawable.extras);
                valorImage = 8;
                break;
            case "FARMACIA":
                imageView.setImageResource(R.drawable.farmacia);
                valorImage = 9;
                break;
            case "ZAPATOS":
                imageView.setImageResource(R.drawable.shoes);
                valorImage = 10;
                break;
        }

        return valorImage;
    }

    private void vaciarEditText(){

        editDescripcion.setText("");
        editCantidad.setText("");
        editPrecio.setText("");
        editNombre.setText("");
        editDesMovimiento.setText("");
    }

}
