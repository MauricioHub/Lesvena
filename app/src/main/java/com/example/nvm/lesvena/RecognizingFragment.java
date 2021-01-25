package com.example.nvm.lesvena;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
/*import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nvm.lesvena.Utils.JSONParser;
import com.example.nvm.lesvena.business.Similitud;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecognizingFragment extends Fragment {
    Button tmp;
    RecyclerView list_item;
    AdapterSimilitud adapter;
    List<Similitud> listSimil = new ArrayList<Similitud>();
    String name;
    float percentage;
    RecyclerView.LayoutManager layoutManager;
    String user, pwd, img = "";

    public RecognizingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Usuario nuevo = new Usuario();

      //  tmp = (Button) view.findViewById(R.id.tmpB);
        list_item = (RecyclerView) view.findViewById(R.id.progress_list);
        layoutManager = new LinearLayoutManager(getActivity());
        list_item.setLayoutManager(layoutManager);
        adapter = new AdapterSimilitud();
        list_item.setAdapter(adapter);

        nuevo = MainActivity.data.getUsuario();
        user = nuevo.getUsername();
        pwd = nuevo.getTokenize();
       // img = "";


        CompareFragment.btnCompare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                try {
                    //String reconocimiento = Reconocimiento(new JSONObject(new String("{\"WINNER\": [\"euptychia\", 0.4602888226509094], \"RESULT\": [[\"hesperiini\", 0.029089363291859627], [\"dalla\", 0.07039333134889603], [\"adelpha\", 0.026514334604144096], [\"eurybie\", 0.12846876680850983], [\"euselasia\", 0.07376862317323685], [\"euptychia\", 0.4602888226509094], [\"astraptes\", 0.02617795765399933], [\"haeterinae\", 0.09708330035209656], [\"carcharodini\", 0.08821552246809006]]}")));
                    new JsonDataTask().execute();
                    Toast.makeText(getActivity().getBaseContext(), "EXITO",
                            Toast.LENGTH_SHORT).show();
                    //view.destroyDrawingCache();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Similitud","Error is: " + e.toString());
                }
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recognizing, container, false);
        return view;
    }

    public String Reconocimiento(JSONObject jsonRespuesta) {
        String datos;
        datos = "qwerty";
    /*    listSimil.clear();
        //jsonObj = jsonRespuesta;
        JSONArray jsonObj = null;
        try {
            jsonObj = jsonRespuesta.getJSONArray("RESULT");
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Error-LoadList: " + e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }
        try {
            int i, veces = 5;
            if(veces > jsonObj.length()){
                veces = jsonObj.length();
            }
            for (i = 0; i < veces; i++) {
                JSONArray row = jsonObj.getJSONArray(i);
                name = row.getString(0);
                percentage = (float) row.getDouble(1);
                //Toast.makeText(getActivity(), "Element: " + name + "=" + percentage, Toast.LENGTH_LONG).show();
                //peopleList.add(this.fillList());

                Similitud simil= new Similitud(name,Float.toString(percentage));
                listSimil.add(simil);
            }
            if(i==veces){
                layoutManager = new LinearLayoutManager(getActivity());
                list_item.setLayoutManager(layoutManager);
                adapter = new AdapterSimilitud();
                list_item.setAdapter(adapter);
                adapter.addAll(listSimil);
            }

        } catch(Exception ex){
            Toast.makeText(getActivity(), "Error-LoadList: " + ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }*/
        return datos;
    }

    private class JsonDataTask extends AsyncTask<String, Integer, String> {
        private ProgressBar miBarraDeProgreso;
        String message;

        @Override
        protected void onPreExecute() {

            miBarraDeProgreso = (ProgressBar) getActivity().findViewById(R.id.progressBarSimilitud);
            miBarraDeProgreso.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... urls) {
            String data, imgBase64 ="";
            MainActivity main;
            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            if(MainActivity.data.getImgBase64List() != null)
                imgBase64 = MainActivity.data.getImgBase64List().get(0);
/*            postParameters.add(new BasicNameValuePair("user", "mmauricio"));
            postParameters.add(new BasicNameValuePair("token", "$2y$04$7RhUmxX.GCUiRWXpKBLHGua9BSD45oU1H9UEViyCiV4q9RFOzIpa."));
            postParameters.add(new BasicNameValuePair("img", "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"));
*/

         /*   postParameters.add(new BasicNameValuePair("user", user));
            postParameters.add(new BasicNameValuePair("token", pwd));
            postParameters.add(new BasicNameValuePair("img", imgBase64));
            //postParameters.add(new BasicNameValuePair("img", "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"));
            */

            postParameters.add(new BasicNameValuePair("user", "admin"));
            postParameters.add(new BasicNameValuePair("token", "$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG"));
            postParameters.add(new BasicNameValuePair("img", "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"));

            String response = null;
            String get_response = null;
            String localLoginUrl = "http://13.85.84.67/lesvena/api/reconocimiento/";
            JSONObject object;
            listSimil.clear();
            try  {
                response =  JSONParser.executeHttpPost(localLoginUrl, postParameters);
                Log.d("Login Activity","Post Response is: " + response);
                object = new JSONObject(response);
                if (object.has("result")){
                    message = object.get("msg").toString();
                    return "false";
                }else{
                    JSONArray jsonObj = null;
                    try {
                        jsonObj = object.getJSONArray("RESULT");
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Error-LoadList: " + e.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                    try {
                        int i, veces = 5;
                        if(veces > jsonObj.length()){
                            veces = jsonObj.length();
                        }
                        for (i = 0; i < veces; i++) {
                            JSONArray row = jsonObj.getJSONArray(i);
                            name = row.getString(0);
                            percentage = (float) row.getDouble(1);
                            //Toast.makeText(getActivity(), "Element: " + name + "=" + percentage, Toast.LENGTH_LONG).show();
                            //peopleList.add(this.fillList());
                            Similitud simil= new Similitud(name,Float.toString(percentage),i);
                            listSimil.add(simil);
                        }
                    } catch(Exception ex){
                        Toast.makeText(getActivity(), "Error-LoadList: " + ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                    return "true";
                }
            }
            catch (Exception e)
            {
                Log.d("Login Activity","Error is: " + e.toString());
                e.printStackTrace();
                return "false";
            }
        }

        protected void onPostExecute(String success) {
            miBarraDeProgreso.setVisibility(View.GONE);
            JSONArray arr = null;
            // try parse the string to a JSON object
            if (success == "true") {
                //finish();
                    layoutManager = new LinearLayoutManager(getActivity());
                    list_item.setLayoutManager(layoutManager);
                    adapter = new AdapterSimilitud();
                    list_item.setAdapter(adapter);
                    adapter.addAll(listSimil);
            } else {
                Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
            }
            //showDialog("Downloaded " + result + " bytes");
        }
    }
}
