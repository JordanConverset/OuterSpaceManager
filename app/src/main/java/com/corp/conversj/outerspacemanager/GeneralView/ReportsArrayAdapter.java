package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        Date date = new Date(aReport.getDate());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRANCE);
        formatter.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String dateFormatted = formatter.format(date);
        holder.tvTime.setText(dateFormatted);

        if(aReport.getType().equals("attacker")){
            if(aReport.getDefenderFleetAfterBattle().getSurvivingShips() != 0){
                //attack lost
                holder.attackWon.setVisibility(View.GONE);
                holder.attackLost.setVisibility(View.VISIBLE);
                holder.defenseWon.setVisibility(View.GONE);
                holder.defenseLost.setVisibility(View.GONE);

                holder.itemView.setBackgroundColor(Color.rgb(100,50,50));
            } else {
                //attack won
                holder.attackWon.setVisibility(View.VISIBLE);
                holder.attackLost.setVisibility(View.GONE);
                holder.defenseWon.setVisibility(View.GONE);
                holder.defenseLost.setVisibility(View.GONE);

                holder.tvGasWon.setText(String.valueOf(aReport.getGasWon()));
                holder.tvMineralsWon.setText(String.valueOf(aReport.getMineralsWon()));

                holder.itemView.setBackgroundColor(Color.rgb(50,100,50));
            }
        } else {
            if(aReport.getDefenderFleetAfterBattle().getSurvivingShips() != 0){
                //defense won
                holder.attackWon.setVisibility(View.GONE);
                holder.attackLost.setVisibility(View.GONE);
                holder.defenseWon.setVisibility(View.VISIBLE);
                holder.defenseLost.setVisibility(View.GONE);

                holder.itemView.setBackgroundColor(Color.rgb(50,100,50));
            } else {
                //defense lost
                holder.attackWon.setVisibility(View.GONE);
                holder.attackLost.setVisibility(View.GONE);
                holder.defenseWon.setVisibility(View.GONE);
                holder.defenseLost.setVisibility(View.VISIBLE);

                holder.tvGasLost.setText(String.valueOf(aReport.getGasWon()));
                holder.tvMineralsLost.setText(String.valueOf(aReport.getMineralsWon()));

                holder.itemView.setBackgroundColor(Color.rgb(100,50,50));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReportClicked(aReport);
            }
        });
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
        private LinearLayout attackWon;
        private LinearLayout attackLost;
        private LinearLayout defenseWon;
        private LinearLayout defenseLost;

        public ReportsViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.rapportDate);
            tvGasWon = (TextView) itemView.findViewById(R.id.rapportMineralsWon);
            tvMineralsWon = (TextView) itemView.findViewById(R.id.rapportGasWon);
            tvGasLost = (TextView) itemView.findViewById(R.id.rapportGasLost);
            tvMineralsLost = (TextView) itemView.findViewById(R.id.rapportMineralsLost);
            attackWon = (LinearLayout) itemView.findViewById(R.id.rapport_attack_won_layout);
            attackLost = (LinearLayout) itemView.findViewById(R.id.rapport_attack_lost_layout);
            defenseWon = (LinearLayout) itemView.findViewById(R.id.rapport_defense_won_layout);
            defenseLost = (LinearLayout) itemView.findViewById(R.id.rapport_defense_lost_layout);
        }
    }
}