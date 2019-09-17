package com.pgrsoft.controlgastos.adaptador;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.fragment.EstadisticaFragment;
import com.pgrsoft.controlgastos.fragment.ListadoDetalleFragment;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;

import java.util.List;

public class ListadoAdaptadores extends RecyclerView.Adapter<ListadoAdaptadores.ListViewHolder> {

    private List<Movimiento> movimientos;
    private MovimientoServices movimientoServices;
    private Context context;

    public ListadoAdaptadores(List<Movimiento> movimientos) {

        this.movimientos = movimientos;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, null, false);
        Log.d("***", "***: ListViewHolder onCreateViewHolder\n: ");
        return new ListViewHolder(view);
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
    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCategory;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewDescription;
        ImageView imageView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategory = (TextView) itemView.findViewById(R.id.idTextViewCategory);
            textViewDate = (TextView) itemView.findViewById(R.id.idTextViewDate);
            textViewDescription = (TextView) itemView.findViewById(R.id.idTextViewDescription);
            textViewName = (TextView) itemView.findViewById(R.id.idTextViewName);
            imageView = (ImageView) itemView.findViewById(R.id.idImageView);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                }
            });



        }
    }


}
