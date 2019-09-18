package com.pgrsoft.controlgastos.adaptador;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;

import java.util.List;

public class ListAdapters extends RecyclerView.Adapter<ListAdapters.ListViewHolder>  {

    private List<Movimiento> movimientos;

    private Context context;
    public final MyListListener myListListener;

    public ListAdapters(List<Movimiento> movimientos, MyListListener myListListener) {

        this.movimientos = movimientos;
        this.myListListener = myListListener;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, null, false);
        Log.d("***", "***: ListViewHolder onCreateViewHolder\n: ");
        return new ListViewHolder(view, this.myListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        holder.textViewCategory.setText(String.valueOf(movimientos.get(position).getProducto().getCategoria().getNombre()));
        holder.textViewName.setText(movimientos.get(position).getProducto().getNombre());
        holder.textViewDescription.setText(movimientos.get(position).getDescripcion());
        holder.textViewDate.setText(movimientos.get(position).getFecha().toString());
        holder.imageView.setImageResource(R.drawable.farmacia);
        //ListViewHolder vh = new ListViewHolder(new View());
        //vh.itemView

    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

    // El metodo es para llamar al objeto y que sea uno:
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewCategory;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewDescription;
        ImageView imageView;

        public ListViewHolder(@NonNull View itemView, MyListListener myListListener) {

            super(itemView);
            textViewCategory = (TextView) itemView.findViewById(R.id.idTextViewCategory);
            textViewDate = (TextView) itemView.findViewById(R.id.idTextViewDate);
            textViewDescription = (TextView) itemView.findViewById(R.id.idTextViewDescription);
            textViewName = (TextView) itemView.findViewById(R.id.idTextViewName);
            imageView = (ImageView) itemView.findViewById(R.id.idImageView);

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


}
