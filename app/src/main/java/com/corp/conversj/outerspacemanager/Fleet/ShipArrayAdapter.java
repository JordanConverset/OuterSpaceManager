package com.corp.conversj.outerspacemanager.Fleet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 13/03/2017.
 */

public class ShipArrayAdapter extends ArrayAdapter<Ship>{
    private final Context context;
    private final ArrayList<Ship> values;
    private final Gson gson;

    public ShipArrayAdapter(Context context, ArrayList<Ship> values) {
        super(context, R.layout.ship_adapter, values);
        this.context = context;
        this.gson = new Gson();
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ship_adapter, parent, false);
        Ship aShip =  values.get(position);
        Button btnBuy = (Button) rowView.findViewById(R.id.buy);
        Button btnPlus = (Button) rowView.findViewById(R.id.plus);
        Button btnMinus = (Button) rowView.findViewById(R.id.minus);
        TextView tvName = (TextView) rowView.findViewById(R.id.name);
        TextView tvLife = (TextView) rowView.findViewById(R.id.life);
        TextView tvMaxAttack = (TextView) rowView.findViewById(R.id.maxAttack);
        TextView tvMinAttack = (TextView) rowView.findViewById(R.id.minAttack);
        TextView tvSpeed = (TextView) rowView.findViewById(R.id.speed);
        TextView tvGasCost = (TextView) rowView.findViewById(R.id.gasCost);
        TextView tvMineralCost = (TextView) rowView.findViewById(R.id.mineralCost);
        TextView tvTimeToBuild = (TextView) rowView.findViewById(R.id.timeToBuild);
        TextView tvLevel = (TextView) rowView.findViewById(R.id.level);
        final TextView tvAmount = (TextView) rowView.findViewById(R.id.amount);
        final int id = aShip.getShipId();
        final String PREFS_NAME = "OuterSpaceManager";
        tvName.setText(aShip.getName());
        tvGasCost.setText(String.valueOf(aShip.getGasCost()));
        tvMineralCost.setText(String.valueOf(aShip.getMineralCost()));
        tvTimeToBuild.setText(String.valueOf(aShip.getTimeToBuild()));
        tvLife.setText(String.valueOf(aShip.getLife()));
        tvMaxAttack.setText(String.valueOf(aShip.getMaxAttack()));
        tvMinAttack.setText(String.valueOf(aShip.getMinAttack()));
        tvSpeed.setText(String.valueOf(aShip.getSpeed()));
        tvLevel.setText(String.valueOf(aShip.getSpatioportLevelNeeded()));

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getContext().getSharedPreferences(PREFS_NAME, 0);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<Ships> request = service.createShip(settings.getString("users", new String()), String.valueOf(id), new Ship(Integer.parseInt(tvAmount.getText().toString())));

                request.enqueue(new Callback<Ships>() {
                    @Override
                    public void onResponse(Call<Ships> call, Response<Ships> response) {
                        if(response.code() == 200) {
                            Context context = getContext();
                            CharSequence text = "En cours de construction";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            Context context = getContext();
                            CharSequence text = "Pas assez de ressources";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ships> call, Throwable t) {
                        Context context = getContext();
                        CharSequence text = "Error";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAmount.setText(String.valueOf(Integer.parseInt(tvAmount.getText().toString())+1));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvAmount.getText().toString())>0)
                    tvAmount.setText(String.valueOf(Integer.parseInt(tvAmount.getText().toString())-1));
            }
        });

        return rowView;
    }
}
