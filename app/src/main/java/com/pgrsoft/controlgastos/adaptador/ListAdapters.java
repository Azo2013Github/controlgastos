package com.pgrsoft.controlgastos.adaptador;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;

import java.util.List;

public class ListAdapters extends RecyclerView.Adapter<ListAdapters.ListViewHolder>  {

    private List<Movimiento> movimientos;

    private Context context;
    public final MyListListener myListListener;

    public ListAdapters(List<Movimiento> movimientos, MyListListener myListListener) {

        this.movimientos = movimientos;
        this.myListListener = myListListener;

    }

    public void setMovimientos(List<Movimiento> movimientos){
        this.movimientos = movimientos;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, null, false);
        return new ListViewHolder(view, this.myListListener);
    }

    // Actualizar los views
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        holder.textViewCategory.setText(String.valueOf(movimientos.get(position).getProducto().getCategoria().getNombre()));
        holder.textViewName.setText(movimientos.get(position).getProducto().getNombre());
        holder.textViewDescription.setText(movimientos.get(position).getDescripcion());
        holder.textViewDate.setText(movimientos.get(position).getFecha().toString());
        holder.imageView.setImageResource(R.drawable.farmacia);
        drawableImage(movimientos.get(position).getProducto().getImagen(), holder.imageView) ;

        if (position%2 == 0) {
            holder.row_linearLayout.setBackgroundResource(R.color.blueLighter);
        }else{
            holder.row_linearLayout.setBackgroundResource(R.color.blueFonce);
        }

    }

    // Los numeros de Filas que hay dentro del ArrayList
    @Override
    public int getItemCount() {
        return movimientos.size();
    }


    // El metodo es para llamar al objeto y que sea uno:
    // creacion de los Views y inicializacion:
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewCategory;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewDescription;
        ImageView imageView;
        LinearLayout row_linearLayout;

        public ListViewHolder(@NonNull View itemView, MyListListener myListListener) {

            super(itemView);
            textViewCategory = (TextView) itemView.findViewById(R.id.idTextViewCategory);
            textViewDate = (TextView) itemView.findViewById(R.id.idTextViewDate);
            textViewDescription = (TextView) itemView.findViewById(R.id.idTextViewDescription);
            textViewName = (TextView) itemView.findViewById(R.id.idTextViewName);
            imageView = (ImageView) itemView.findViewById(R.id.idImageView);
            row_linearLayout = (LinearLayout) itemView.findViewById(R.id.idLinearLayout);
            // Creacion del Listener personalize
            myListListener = myListListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myListListener.myClickList(getAdapterPosition());
        }

    }

    // creacion de una interface para poder acceder a myClickList
    public interface MyListListener{
        public void myClickList(int position);

    }

    // Poner las imagenes
    private void drawableImage(int valueImage, ImageView imageView){

        switch (valueImage){
            case 1:
                imageView.setImageResource(R.drawable.carne);
                break;
            case 2:
                imageView.setImageResource(R.drawable.verduras);
                break;
            case 3:
                imageView.setImageResource(R.drawable.grifo);
                break;
            case 4:
                imageView.setImageResource(R.drawable.legumbres);

                break;
            case 5:
                imageView.setImageResource(R.drawable.bebidas);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ropa);
                break;

            case 7:
                imageView.setImageResource(R.drawable.casa);
                break;
            case 8:
                imageView.setImageResource(R.drawable.extras);

                break;
            case 9:
                imageView.setImageResource(R.drawable.farmacia);

                break;
            case 10:
                imageView.setImageResource(R.drawable.shoes);
                break;
            case 11:
                imageView.setImageResource(R.drawable.ic_tmb);
                break;
            case 12:
                imageView.setImageResource(R.drawable.ic_fruta);
                break;
            default: imageView.setImageResource(R.drawable.brocoli);
        }

    }



}
