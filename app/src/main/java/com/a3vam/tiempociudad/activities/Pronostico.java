package com.a3vam.tiempociudad.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a3vam.tiempociudad.R;
import com.a3vam.tiempociudad.adapters.AdapterPronostico;
import com.a3vam.tiempociudad.model.List;
import com.a3vam.tiempociudad.model.Main;
import com.a3vam.tiempociudad.model.Weather;
import com.a3vam.tiempociudad.services.ServiceManager;
import com.a3vam.tiempociudad.services.callbacks.Callbackpronostico;
import com.google.android.gms.vision.text.Text;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pronostico extends BaseActivity {
    String mIdCity2go;
    JsonObject jsonObject;
    ArrayList<Main> principal;
    AdapterPronostico adapter;
    TextView tvNombreCiudad;
    private String mNombreCiudad;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronostico);

        mIdCity2go = getIntent().getExtras().get("id").toString();
        mNombreCiudad = getIntent().getExtras().get("nombreciudad").toString();

        //Toast.makeText(this,mIdCity2go,Toast.LENGTH_SHORT).show();
        principal = new ArrayList<Main>();
        list = (ListView) findViewById(R.id.lvPronostico);
        tvNombreCiudad = (TextView) findViewById(R.id.tvCiudad);
        tvNombreCiudad.setText(mNombreCiudad);
        cargarPronostico(mIdCity2go);
        adapter = new AdapterPronostico(Pronostico.this, principal);
        list.setAdapter(adapter);





    }


    private void cargarPronostico(String id)
    {
        ServiceManager.getPronosticoCiudad(id, new Callbackpronostico() {
            @Override
            public void onSuccess(JsonObject value) {
                jsonObject = value;
                Gson gson = new Gson();

                for (JsonElement j : value.getAsJsonArray("list")) {
                    Main m = gson.fromJson(j.getAsJsonObject().get("main"),Main.class);
                    principal.add(m);
                }

                adapter = new AdapterPronostico(Pronostico.this, principal);
                list.setAdapter(adapter);

                //Log.i(getLocalClassName(), jsonObject.getAsString());

            }

            @Override
            public void onError(int errorCode, String errorMessage) {

            }
        });

    }



}
