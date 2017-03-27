package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;

import java.util.ArrayList;

/**
 * Created by conversj on 21/03/2017.
 */
public class ReportsArrayAdapter extends RecyclerView.Adapter<ReportsArrayAdapter.ReportsViewHolder> {
    private final Context context;
    private final ArrayList<Report> reports;
    private OnGeneralClickedListener listener;

    public ReportsArrayAdapter(Context context, ArrayList<Report> reports) {
        this.reports = reports;
        this.context = context;
    }

    @Override
    public ReportsArrayAdapter.ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reports_adapter, parent, false);
        ReportsArrayAdapter.ReportsViewHolder viewHolder = new ReportsArrayAdapter.ReportsViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsArrayAdapter.ReportsViewHolder holder, int position) {
        final Report aReport = reports.get(position);
        if(aReport.getType().equals("attacker")){
            if(aReport.getAttackerFleetAfterBattle().getSurvivingShips() == 0){
                //attack lost
                holder.attackWon.setVisibility(View.GONE);
                holder.attackLost.setVisibility(View.VISIBLE);
                holder.defenseWon.setVisibility(View.GONE);
                holder.defenseLost.setVisibility(View.GONE);
            } else {
                //attack won
                holder.attackWon.setVisibility(View.VISIBLE);
                holder.attackLost.setVisibility(View.GONE);
                holder.defenseWon.setVisibility(View.GONE);
                holder.defenseLost.setVisibility(View.GONE);

                holder.tvGasWon.setText(String.valueOf(aReport.getGasWon()));
                holder.tvMineralsWon.setText(String.valueOf(aReport.getMineralsWon()));
            }
        } else {
            if(aReport.getAttackerFleetAfterBattle().getSurvivingShips() == 0){
                //defense won
            } else {
                //defense lost
            }
        }
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public void setListener(OnGeneralClickedListener listener) {
        this.listener = listener;
    }

    public class ReportsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvGasWon;
        private TextView tvMineralsWon;
        private TextView tvGasLost;
        private TextView tvMineralsLost;
        private TextView tvDefeat;
        private TextView tvDefended;
        private LinearLayout attackWon;
        private LinearLayout attackLost;
        private LinearLayout defenseWon;
        private LinearLayout defenseLost;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.rapportDate);
            tvGasWon = (TextView) itemView.findViewById(R.id.rapportMineralsWon);
            tvMineralsWon = (TextView) itemView.findViewById(R.id.rapportGasWon);
            tvDefeat = (TextView) itemView.findViewById(R.id.rapportDefeat);
            tvGasLost = (TextView) itemView.findViewById(R.id.rapportGasLost);
            tvMineralsLost = (TextView) itemView.findViewById(R.id.rapportMineralsLost);
            tvDefended = (TextView) itemView.findViewById(R.id.rapportDefended);
            attackWon = (LinearLayout) itemView.findViewById(R.id.rapport_attack_won_layout);
            attackLost = (LinearLayout) itemView.findViewById(R.id.rapport_attack_lost_layout);
            defenseWon = (LinearLayout) itemView.findViewById(R.id.rapport_defense_won_layout);
            defenseLost = (LinearLayout) itemView.findViewById(R.id.rapport_defense_lost_layout);
        }
    }
}