package com.corp.adrisher.appsqllite.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.corp.adrisher.appsqllite.R;
import com.corp.adrisher.appsqllite.VerDatos;
import com.corp.adrisher.appsqllite.entidades.Datos;

import java.util.ArrayList;

public class ListaDatosAdapter extends RecyclerView.Adapter<ListaDatosAdapter.DatosViewHolder> {

    ArrayList<Datos> listaDatos;

    public ListaDatosAdapter(ArrayList<Datos> listaDatos){
        this.listaDatos = listaDatos;

    }
    @NonNull
    @Override
    public DatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_persona, null, false);
        return new DatosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosViewHolder holder, int position) {
        holder.viewNombre.setText(listaDatos.get(position).getNombre());
        holder.viewTelefono.setText(listaDatos.get(position).getTelefono());
        holder.viewCorreo.setText(listaDatos.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
       return listaDatos.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo;

        public DatosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, VerDatos.class);
                    intent.putExtra("ID", listaDatos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
