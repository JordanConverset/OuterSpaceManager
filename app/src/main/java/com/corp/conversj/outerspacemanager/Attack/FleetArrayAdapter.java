package com.corp.conversj.outerspacemanager.Attack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.DB.ShipDataSource;
import com.corp.conversj.outerspacemanager.Model.Ship;
import com.corp.conversj.outerspacemanager.Model.Ships;
import com.corp.conversj.outerspacemanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac15 on 14/03/2017.
 */

public class FleetArrayAdapter extends RecyclerView.Adapter<FleetArrayAdapter.FleetViewHolder> {
    private final Context context;
    private final List<Ship> ships;
    private final Ships fleet = new Ships(new ArrayList<Ship>());

    public FleetArrayAdapter(Context context, List<Ship> ships) {
        this.ships = ships;
        this.context = context;
    }

    @Override
    public FleetArrayAdapter.FleetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fleet_adapter, parent, false);
        FleetArrayAdapter.FleetViewHolder viewHolder = new FleetArrayAdapter.FleetViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FleetArrayAdapter.FleetViewHolder holder, int position) {
        final Ship aShip =  ships.get(position);
        final int id = aShip.getShipId();
        final FleetArrayAdapter.FleetViewHolder theHolder = holder;
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
        holder.tvAmountAvailable.setText(String.valueOf(aShip.getAmount()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ship sendShip = new Ship(Integer.parseInt(theHolder.tvAmount.getText().toString()),id);
                fleet.addShip(sendShip);
                ShipDataSource db = new ShipDataSource(context);
                db.open();
                com.corp.conversj.outerspacemanager.DB.Ship dbShip = new com.corp.conversj.outerspacemanager.DB.Ship();
                dbShip.setName(theHolder.tvName.getText().toString());
                dbShip.setId(sendShip.getShipId());
                db.createShip(dbShip);
                CharSequence text = "Ships Added";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(theHolder.tvAmountAvailable.getText().toString())>Integer.parseInt(theHolder.tvAmount.getText().toString()))
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

    public class FleetViewHolder extends RecyclerView.ViewHolder {
        private Button btnAdd;
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
        private TextView tvAmountAvailable;

        public FleetViewHolder(View itemView) {
            super(itemView);
            btnAdd = (Button) itemView.findViewById(R.id.add);
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
            tvAmountAvailable = (TextView) itemView.findViewById(R.id.amountAvailable);
            tvAmount = (EditText) itemView.findViewById(R.id.amount);

        }
    }

    public Ships getFleet(){
        return fleet;
    }


}
