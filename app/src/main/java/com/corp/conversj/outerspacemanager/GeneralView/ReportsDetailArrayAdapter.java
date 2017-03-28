package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.DB.ShipDataSource;
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
    private final ArrayList<Ship> ships;

    public ReportsDetailArrayAdapter(Context context, ArrayList<Ship> ships) {
        this.ships = ships;
        this.context = context;
    }

    @Override
    public ReportsDetailArrayAdapter.ReportsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.battle_fleet_adapter, parent, false);
        ReportsDetailArrayAdapter.ReportsDetailViewHolder viewHolder = new ReportsDetailArrayAdapter.ReportsDetailViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsDetailArrayAdapter.ReportsDetailViewHolder holder, int position) {
        final Ship aShip =  ships.get(position);
        ShipDataSource db = new ShipDataSource(context);
        db.open();
        String name = db.getShipNameById(aShip.getShipId());
        db.close();
        holder.tvName.setText(name);
        holder.tvAmount.setText(String.valueOf(aShip.getAmount()));

    }

    @Override
    public int getItemCount() {
        if(ships == null)
            return 0;
        else
            return ships.size();
    }

    public class ReportsDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAmount;
        private TextView tvName;

        public ReportsDetailViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAmount = (TextView) itemView.findViewById(R.id.amount);
        }
    }
}