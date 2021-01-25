package com.example.nvm.lesvena;

import android.app.Activity;
import android.content.Context;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.nvm.lesvena.business.Similitud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bonilla-Lopez on 10/9/2017.
 */

public class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    List<Similitud> simil;

    public CustomAdapter(FragmentActivity context, int layoutResourceId, List<Similitud> simil) {
        super(context, layoutResourceId);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.simil = simil;
    }

    static class UserHolder {
        TextView name_tv;
        ProgressBar progressBar;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.name_tv= (TextView)row.findViewById(R.id.name_tv);
            holder.progressBar = (ProgressBar)row.findViewById(R.id.prg_bar);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final HashMap<String, String> user = data.get(position);
        Similitud similitud_one = simil.get(position);
        holder.name_tv.setText(similitud_one.getName());//user.get("name"));
        holder.progressBar.setMax(100);
        // holder.progressBar.setMin(0);
        float value = Float.valueOf(similitud_one.getPercentage());//user.get("percentage"));
        value=value*100;
        holder.progressBar.setProgress((int) value);
        holder.progressBar.setIndeterminate(false);
       // holder.progressBar.setBackgroundResource(R.color.color60);
        // holder.progressBar.setProgressBackgroundTintMode(ProgressBar.TIN);

        notifyDataSetChanged();
        return row;
    }




    /*public CustomAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
       TextView name_tv = (TextView)view.findViewById(R.id.name_tv);
        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.prg_bar);
        String name =cursor.getColumnName()
    }*/


}
