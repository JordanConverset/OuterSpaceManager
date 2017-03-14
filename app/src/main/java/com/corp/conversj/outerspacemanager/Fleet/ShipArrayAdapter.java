package com.corp.conversj.outerspacemanager.Fleet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Attack.FleetArrayAdapter;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 13/03/2017.
 */

public class ShipArrayAdapter extends RecyclerView.Adapter<ShipArrayAdapter.ShipViewHolder>{
    private final Context context;
    private final List<Ship> ships;

    public ShipArrayAdapter(Context context, List<Ship> ships) {
        this.ships = ships;
        this.context = context;
    }

    @Override
    public ShipArrayAdapter.ShipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ship_adapter, parent, false);
        ShipArrayAdapter.ShipViewHolder viewHolder = new ShipArrayAdapter.ShipViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShipArrayAdapter.ShipViewHolder holder, int position) {
        final Ship aShip =  ships.get(position);
        final int id = aShip.getShipId();
        final String PREFS_NAME = "OuterSpaceManager";
        final ShipArrayAdapter.ShipViewHolder theHolder = holder;
        holder.tvName.setText(aShip.getName());
        holder.tvGasCost.setText(String.valueOf(aShip.getGasCost()));
        holder.tvMineralCost.setText(String.valueOf(aShip.getMineralCost()));
        holder.tvTimeToBuild.setText(String.valueOf(aShip.getTimeToBuild()));
        holder.tvLife.setText(String.valueOf(aShip.getLife()));
        holder.tvMaxAttack.setText(String.valueOf(aShip.getMaxAttack()));
        holder.tvMinAttack.setText(String.valueOf(aShip.getMinAttack()));
        holder.tvSpeed.setText(String.valueOf(aShip.getSpeed()));
        holder.tvShield.setText(String.valueOf(aShip.getShield()));
        holder.tvCapacity.setText(String.valueOf(aShip.getCapacity()));
        holder.tvLevel.setText(String.valueOf(aShip.getSpatioportLevelNeeded()));

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<Ships> request = service.createShip(settings.getString("users", new String()), String.valueOf(id), new Ship(Integer.parseInt(theHolder.tvAmount.getText().toString())));

                request.enqueue(new Callback<Ships>() {
                    @Override
                    public void onResponse(Call<Ships> call, Response<Ships> response) {
                        if(response.code() == 200) {
                            CharSequence text = "En cours de construction";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            CharSequence text = "Pas assez de ressources";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ships> call, Throwable t) {
                        CharSequence text = "Error";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theHolder.tvAmount.setText(String.valueOf(Integer.parseInt(theHolder.tvAmount.getText().toString())+1));
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(theHolder.tvAmount.getText().toString())>0)
                    theHolder.tvAmount.setText(String.valueOf(Integer.parseInt(theHolder.tvAmount.getText().toString())-1));
            }
        });

        holder.tvAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    Integer.parseInt(s.toString());
                } catch (Exception e) {
                    theHolder.tvAmount.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    Integer.parseInt(s.toString());
                } catch (Exception e) {
                    theHolder.tvAmount.setText("0");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ships.size();
    }

    public class ShipViewHolder extends RecyclerView.ViewHolder {
        private Button btnBuy;
        private Button btnPlus;
        private Button btnMinus;
        private TextView tvName;
        private TextView tvLife;
        private TextView tvMaxAttack;
        private TextView tvMinAttack;
        private TextView tvSpeed;
        private TextView tvShield;
        private TextView tvCapacity;
        private TextView tvGasCost;
        private TextView tvMineralCost;
        private TextView tvTimeToBuild;
        private EditText tvAmount;
        private TextView tvLevel;

        public ShipViewHolder(View itemView) {
            super(itemView);
            btnBuy = (Button) itemView.findViewById(R.id.buy);
            btnPlus = (Button) itemView.findViewById(R.id.plus);
            btnMinus = (Button) itemView.findViewById(R.id.minus);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvLife = (TextView) itemView.findViewById(R.id.life);
            tvMaxAttack = (TextView) itemView.findViewById(R.id.maxAttack);
            tvMinAttack = (TextView) itemView.findViewById(R.id.minAttack);
            tvSpeed = (TextView) itemView.findViewById(R.id.speed);
            tvShield = (TextView) itemView.findViewById(R.id.shield);
            tvCapacity = (TextView) itemView.findViewById(R.id.capacity);
            tvGasCost = (TextView) itemView.findViewById(R.id.gasCost);
            tvMineralCost = (TextView) itemView.findViewById(R.id.mineralCost);
            tvTimeToBuild = (TextView) itemView.findViewById(R.id.timeToBuild);
            tvLevel = (TextView) itemView.findViewById(R.id.level);
            tvAmount = (EditText) itemView.findViewById(R.id.amount);

        }
    }
}
