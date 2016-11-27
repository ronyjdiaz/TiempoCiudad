package com.a3vam.tiempociudad.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3vam.tiempociudad.R;
import com.a3vam.tiempociudad.model.Main;

import java.util.ArrayList;

/**
 * Created by rony_2 on 27/11/2016.
 */

public class AdapterPronostico extends ArrayAdapter<Main> {

    ArrayList<Main>lista;
    Context context;
    private static LayoutInflater inflater=null;

    public AdapterPronostico(Context context,ArrayList<Main> lista){
        super(context, 0, lista);


        this.lista = lista;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    private class ViewHolder {

        TextView tvTemp;
        TextView tvTempMaxima;
        TextView tvTempMinima;
    }
    @Override
    public int getCount() {
        return lista.size();
    }




    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Main pronostico = (Main) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        TextView tvTemp = (TextView) convertView.findViewById(R.id.tvTemperatura);
        TextView tvTempMaxima = (TextView) convertView.findViewById(R.id.tvTemperaturaMinima);
        TextView tvTempMinima = (TextView) convertView.findViewById(R.id.tvTemperaturaMinima);


        tvTemp.setText( String.valueOf(pronostico.getTemp()));
        tvTempMaxima.setText( String.valueOf(pronostico.getTempMax()));
        tvTempMinima.setText( String.valueOf(pronostico.getTempMin()));

        // Return the completed view to render on screen
        return convertView;
    }



}
