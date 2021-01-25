package com.example.nvm.lesvena;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
/*import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amigold.fundapter.FunDapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DtsListFragment extends Fragment {
    private DatasetAdmin admBase;
  /*  public static ArrayList<Dataset> datasetList = null;
    public static FunDapter dapter;
    public static ListView listview;*/

    private RecyclerView recyclerViewDts;
    private RecyclerViewAdaptador adaptadorDts;

    private Context contexto;
    private LesvenaUtil utiLes;


    public DtsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dts_list, container, false);
        contexto = getActivity().getBaseContext();
        admBase = new DatasetAdmin(contexto);
        utiLes = new LesvenaUtil(contexto);
        List<Dataset> obtenerCan = obtenerListadoDataset();

        recyclerViewDts = (RecyclerView) view.findViewById(R.id.recyclerDtsList);
        recyclerViewDts.setLayoutManager(new LinearLayoutManager(contexto));
        adaptadorDts = new RecyclerViewAdaptador(obtenerCan);
        recyclerViewDts.setAdapter(adaptadorDts);

        return view;
    }


    public List<Dataset> obtenerListadoDataset(){
        List<Dataset> datasetList = admBase.readDatasetTable();

        try{
            for(int i=0; i<datasetList.size(); i++){
                Dataset loadData = datasetList.get(i);
                Bitmap nuevoBitmap = utiLes.getPictureDataset(loadData.getNombreImagen());
                Clasificado nuevoClasificado = admBase.readUnitClasificadoTable(
                        loadData.getClasificado_id());
                Estado nuevoEstado = admBase.readUnitEstadoTable(
                        loadData.getParte_id());
                Clasificacion nuevaClasificacion = admBase.readUnitClasificacionTable(
                        nuevoClasificado.getClasificacionId());

                loadData.setCardBitmap(nuevoBitmap);
                loadData.setEstado(nuevoEstado);
                loadData.setClasificacion(nuevaClasificacion);
/*                System.out.println("DATASET-" + nuevoBitmap);
                System.out.println("DATASET-" + nuevoEstado.getEstado());
                System.out.println("DATASET-" + nuevaClasificacion.getNombre_comun());
                System.out.println("DATASET-" + loadData.getLatitud()+ "/" + loadData.getLongitud() );*/
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return datasetList;
    }

/*
    public List<Dataset> obtenerCantantes(){
        List<Dataset> cantante = new ArrayList<>();
        cantante.add(new Dataset("ScarlettBATA", "Rusa", R.mipmap.scarlett1));
        cantante.add(new Dataset("ScarlettFORMAL", "Rusa", R.mipmap.scarlett2));
        cantante.add(new Dataset("ScarlettGALA", "Rusa", R.mipmap.scarlett3));
        return cantante;
    }*/

}
