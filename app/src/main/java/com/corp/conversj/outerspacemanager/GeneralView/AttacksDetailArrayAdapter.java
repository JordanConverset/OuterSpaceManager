package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.DB.ShipDataSource;
import com.corp.conversj.outerspacemanager.Model.Ship;
import com.corp.conversj.outerspacemanager.Model.Ships;
import com.corp.conversj.outerspacemanager.R;

import java.util.List;

/**
 * Created by conversj on 21/03/2017.
 */
public class AttacksDetailArrayAdapter extends RecyclerView.Adapter<AttacksDetailArrayAdapter.AttacksDetailViewHolder> {
    private final Context context;
    private final List<Ship> ships;

    public AttacksDetailArrayAdapter(Context context, Ships ships) {
        this.ships = ships.getShips();
        this.context = context;
    }

    @Override
    public AttacksDetailArrayAdapter.AttacksDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.attacks_detail_adapter, parent, false);
        AttacksDetailArrayAdapter.AttacksDetailViewHolder viewHolder = new AttacksDetailArrayAdapter.AttacksDetailViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AttacksDetailArrayAdapter.AttacksDetailViewHolder holder, int position) {
        Ship aShip = ships.get(position);
        ShipDataSource db = new ShipDataSource(context);
        db.open();
        String name = db.getShipNameById(aShip.getShipId());
        db.close();
        holder.tvName.setText(name);
        holder.tvAmount.setText(String.valueOf(aShip.getAmount()));
    }

    @Override
    public int getItemCount() {
        return ships.size();
    }

    public class AttacksDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAmount;

        public AttacksDetailViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAmount = (TextView) itemView.findViewById(R.id.amount);
        }
    }
}
