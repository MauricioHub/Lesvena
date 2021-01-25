package com.example.nvm.lesvena;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nvm.lesvena.business.Similitud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariamargoth on 11/9/2017.
 */

public class AdapterSimilitud extends RecyclerView.Adapter<AdapterSimilitud.ViewHolderSimilitud> {
    private List<Similitud> listSimil;
    public int numberPosition = 0;
     public AdapterSimilitud(){
         this.listSimil=new ArrayList<>();
     }
    public void addAll(final List<Similitud> simil) {
        if (simil != null) {
            this.listSimil = new ArrayList<Similitud>();
            this.listSimil.addAll(simil);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolderSimilitud onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolderSimilitud(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSimilitud viewHolderSimilitud, int positionHolder) {
        Similitud similitud = listSimil.get(positionHolder);
        viewHolderSimilitud.position = positionHolder;
        viewHolderSimilitud.txtname.setText(similitud.getName());
        numberPosition = positionHolder;

        viewHolderSimilitud.progressBar.setMax(100);
        // holder.progressBar.setMin(0);
        float value = Float.valueOf(similitud.getPercentage());
        value=value*100;
        viewHolderSimilitud.percentage.setText(String.format("%.2f", value) + "%");
        viewHolderSimilitud.progressBar.setProgress((int) value);
        viewHolderSimilitud.progressBar.setIndeterminate(false);

        viewHolderSimilitud.btnDetail.setOnClickListener(new View.OnClickListener() {
            Similitud similitud = listSimil.get(numberPosition);

            @Override
            public void onClick(View v){
                //System.out.println("hOLA-" + similitud.getPositionSimil());
                DetailFragment.loadDetailView(similitud.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSimil.size();
    }

    public class ViewHolderSimilitud extends RecyclerView.ViewHolder {
        public TextView txtname;
        public ProgressBar progressBar;
        public TextView percentage;
        public int position;
     /*   public Button btnDetail;
      */
        public ImageView btnDetail;

        public ViewHolderSimilitud(View itemView) {
            super(itemView);
            txtname = (TextView) itemView.findViewById(R.id.name_tv);
            progressBar = (ProgressBar) itemView.findViewById(R.id.prg_bar);
            percentage = (TextView) itemView.findViewById(R.id.percentage);
            btnDetail = (ImageView) itemView.findViewById(R.id.btn_detail);
        }
    }
}
