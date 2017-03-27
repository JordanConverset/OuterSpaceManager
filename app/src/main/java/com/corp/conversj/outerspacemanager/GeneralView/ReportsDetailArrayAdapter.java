package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.Model.Ship;
import com.corp.conversj.outerspacemanager.Model.Ships;
import com.corp.conversj.outerspacemanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conversj on 21/03/2017.
 */
public class ReportsDetailArrayAdapter extends RecyclerView.Adapter<ReportsDetailArrayAdapter.ReportsDetailViewHolder> {
    private final Context context;
    private final List<Ship> ships;
    private final Ships fleet = new Ships(new ArrayList<Ship>());

    public ReportsDetailArrayAdapter(Context context, List<Ship> ships) {
        this.ships = ships;
        this.context = context;
    }

    @Override
    public ReportsDetailArrayAdapter.ReportsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fleet_adapter, parent, false);
        ReportsDetailArrayAdapter.ReportsDetailViewHolder viewHolder = new ReportsDetailArrayAdapter.ReportsDetailViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsDetailArrayAdapter.ReportsDetailViewHolder holder, int position) {
        final Ship aShip =  ships.get(position);
        final int id = aShip.getShipId();
        final ReportsDetailArrayAdapter.ReportsDetailViewHolder theHolder = holder;
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

    }

    @Override
    public int getItemCount() {
        return ships.size();
    }

    public class ReportsDetailViewHolder extends RecyclerView.ViewHolder {
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

        public ReportsDetailViewHolder(View itemView) {
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
}