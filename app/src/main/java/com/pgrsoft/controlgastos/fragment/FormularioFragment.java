package com.pgrsoft.controlgastos.fragment;


//import android.app.FragmentTransaction;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
    //private EditText editCantidad;
    private ImageView imageView;

    private ArrayAdapter<String> stringArrayAdapter;
    private List<Categoria> categorias;

    private MovimientoServices movimientoServices;
    private ProductoServices productoServices;
    private CategoriaServices categoriaServices;

    private Categoria categoria;
    private Producto producto;
    private Movimiento movimiento;

    // La parte de la foto:
    private int camera = 1,gellary = 2;
    public static final int PERMISSION_CODE = 111;

    private Button btnPhoto;
    private Button btnSave;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        btnAdd = (Button) miVista.findViewById(R.id.idBtnAdd);
        //btnList = (Button) miVista.findViewById(R.id.idBtnList);

        spinner = (Spinner) miVista.findViewById(R.id.idSpinnerCategory);
        loadSpinner();

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        //editCantidad = (EditText) miVista.findViewById(R.id.idCantidad);
        editDesMovimiento = (EditText) miVista.findViewById(R.id.idDesMovimiento);
        imageView = (ImageView) miVista.findViewById(R.id.idImage);

        btnPhoto = (Button) miVista.findViewById(R.id.idBtnCamera);

        categoriaServices = new CategoriaServicesImpl(this.getActivity());
        productoServices = new ProductoServicesImpl(this.getActivity());
        movimientoServices = new MovimientoServicesImpl(this.getActivity());

        btnAdd.setOnClickListener(this);
        btnPhoto.setOnClickListener(this); //editCantidad.getText().toString().equals("") ||Double.parseDouble(editCantidad.getText().toString()) *

        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idBtnAdd:
                if (spinner.toString().equals("") || editPrecio.getText().toString().equals("") ||
                    editDesMovimiento.getText().toString().equals("") || editNombre.getText().toString().equals("")
                    || editDescripcion.getText().toString().equals("")){
                    Log.d("***", "The EditTexts are Empty ");
                }else{
                    String strCategoria = spinner.getSelectedItem().toString();
                    String nombre = editNombre.getText().toString();
                    double precio = Double.parseDouble(editPrecio.getText().toString());
                    double importe =  Double.parseDouble(editPrecio.getText().toString());

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
                emptyEditText();
                break;

            case R.id.idBtnCamera:
                OpenImages();
                break;

        }

    }

    private void loadSpinner(){

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
        Categoria categoria11 = new Categoria("TRANSPORTES");
        Categoria categoria12 = new Categoria("FRUTAS");

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
        categorias.add(categoria11);
        categorias.add(categoria12);

        //int i = 0;
        String [] strNombres = new String [12];

        /*for (Categoria categoria: categorias) {
            strNombres [i] = categoria.getNombre(); //cogemos los nombres de los agentes en cada posicion
            i++; //Incrementamos el i para que salga todos los nombres de los agentes
        }*/
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
            strNombres [i] = categoria.getNombre();
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
            case "TRANSPORTES":
                imageView.setImageResource(R.drawable.ic_tmb);
                valorImage = 11;
                break;
            case "FRUTAS":
                imageView.setImageResource(R.drawable.ic_fruta);
                valorImage = 12;
                break;

            default: imageView.setImageResource(R.drawable.brocoli);
        }

        return valorImage;
    }

    private void emptyEditText(){

        editDescripcion.setText("");
        editPrecio.setText("");
        editNombre.setText("");
        editDesMovimiento.setText("");
    }

    public boolean checkPermission(){

        int result = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CAMERA);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2
                == PackageManager.PERMISSION_GRANTED;

    }

    public void requestPermission(){

        ActivityCompat.requestPermissions(this.getActivity(),new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                PERMISSION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case PERMISSION_CODE :

                if (grantResults.length > 0){

                    boolean storage  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameras = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storage && cameras){

                        Toast.makeText(this.getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(this.getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                                showMsg("You need to allow access to the permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},PERMISSION_CODE);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
        }

    }

    private void showMsg(String s, DialogInterface.OnClickListener listener) {

        new AlertDialog.Builder(this.getActivity())
                .setMessage(s)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void OpenImages() {

        final CharSequence[] option = {"Camera","Gellary"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Select Action");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (option[which].equals("Camera")){
                    CameraIntent();
                }
                if (option[which].equals("Gellary")){
                    GellaryIntent();

                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void GellaryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select File"),gellary);
    }

    private void CameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,camera);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == camera){
                OpenCameraResult(data);
            }else if (requestCode == gellary){
                OpenGellaryResult(data);
            }
        }
    }

    private void OpenGellaryResult(Intent data) {

        Bitmap bitmap = null;

        if (data != null){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(),data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageView.setImageBitmap(bitmap);

    }

    // Primera funcion:
    private void OpenCameraResult(Intent data) {

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File paths = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Toast.makeText(this.getActivity(), "Path -> " + paths.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        Log.d("tag", "File Path -> " + paths.getName());

        try {
            FileOutputStream fos = new FileOutputStream(paths);
            paths.createNewFile();

            if (!paths.exists()) {
                paths.mkdir();
            }

            fos.write(bytes.toByteArray());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);

    }


}
