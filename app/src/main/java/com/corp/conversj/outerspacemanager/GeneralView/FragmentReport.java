package com.corp.conversj.outerspacemanager.GeneralView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;

/**
 * Created by mac15 on 27/03/2017.
 */

public class FragmentReport extends Fragment {
    private RecyclerView rvAttackerFleetAfterBattle;
    private RecyclerView rvAttackerFleetBeforeBattle;
    private RecyclerView rvDefenderFleetAfterBattle;
    private RecyclerView rvDefenderFleetBeforeBattle;
    private TextView tvTo;
    private TextView tvFrom;
    private TextView tvSurvivingAttackerShip;
    private TextView tvSurvivingDefenderShip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_detail,container, false);
        rvAttackerFleetAfterBattle = (RecyclerView) v.findViewById(R.id.attackerFleetAfter);
        rvAttackerFleetBeforeBattle = (RecyclerView) v.findViewById(R.id.attackerFleetBefore);
        rvDefenderFleetAfterBattle = (RecyclerView) v.findViewById(R.id.defenderFleetAfter);
        rvDefenderFleetBeforeBattle = (RecyclerView) v.findViewById(R.id.defenderFleetBefore);
        tvTo = (TextView) v.findViewById(R.id.to);
        tvFrom = (TextView) v.findViewById(R.id.from);
        tvSurvivingAttackerShip = (TextView) v.findViewById(R.id.survivingAttackerShip);
        tvSurvivingDefenderShip = (TextView) v.findViewById(R.id.survivingDefenderShip);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvAttackerFleetAfterBattle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvAttackerFleetBeforeBattle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvDefenderFleetAfterBattle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvDefenderFleetBeforeBattle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    public void setReport(Report report) {
        rvAttackerFleetAfterBattle.setAdapter(new ReportsDetailArrayAdapter(getActivity().getApplicationContext(), report.getAttackerFleetAfterBattle().getFleet()));
        rvAttackerFleetBeforeBattle.setAdapter(new ReportsDetailArrayAdapter(getActivity().getApplicationContext(), report.getAttackerFleet()));
        rvDefenderFleetAfterBattle.setAdapter(new ReportsDetailArrayAdapter(getActivity().getApplicationContext(), report.getDefenderFleetAfterBattle().getFleet()));
        rvDefenderFleetBeforeBattle.setAdapter(new ReportsDetailArrayAdapter(getActivity().getApplicationContext(), report.getDefenderFleet()));
        tvTo.setText(report.getTo());
        tvFrom.setText(report.getFrom());
        tvSurvivingAttackerShip.setText(String.valueOf(report.getAttackerFleetAfterBattle().getSurvivingShips()));
        tvSurvivingDefenderShip.setText(String.valueOf(report.getDefenderFleetAfterBattle().getSurvivingShips()));
    }
}
