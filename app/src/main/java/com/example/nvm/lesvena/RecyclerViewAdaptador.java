package com.example.nvm.lesvena;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by NVM on 11/6/2017.
 */

public class RecyclerViewAdaptador  extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dtComun, dtCientifico, dtEstado, dtGeolocal, dtFecha;
        ImageView fotoEspecie;

        public ViewHolder(View itemView){
            super(itemView);
            dtComun = (TextView) itemView.findViewById(R.id.dtComun);
            dtCientifico = (TextView) itemView.findViewById(R.id.dtCientifico);
            dtEstado = (TextView) itemView.findViewById(R.id.dtEstado);
            dtGeolocal = (TextView) itemView.findViewById(R.id.dtGeolocal);
            dtFecha = (TextView) itemView.findViewById(R.id.dtFecha);
            fotoEspecie = (ImageView) itemView.findViewById(R.id.imgEspecie);
        }
    }

    public List<Dataset> datasetLista;

    public RecyclerViewAdaptador(List<Dataset> datasetLista){
        this.datasetLista = datasetLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dataset,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.dtComun.setText(datasetLista.get(position).getClasificacion().getNombre_comun());
        holder.dtCientifico.setText(datasetLista.get(position).getClasificacion().getNombre_cientifico());
        holder.dtEstado.setText  ("ESTADO:  " + datasetLista.get(position).getEstado().getEstado());
        holder.dtGeolocal.setText("LATITUD: " + datasetLista.get(position).getLatitud() +
                ",  LONGITUD: " + datasetLista.get(position).getLongitud());
        holder.dtFecha.setText   ("CAPTUR.: " + datasetLista.get(position).getFecha());
        holder.fotoEspecie.setImageBitmap(datasetLista.get(position).getCardBitmap());
    }

    @Override
    public int getItemCount() {
        return datasetLista.size();
    }

}
