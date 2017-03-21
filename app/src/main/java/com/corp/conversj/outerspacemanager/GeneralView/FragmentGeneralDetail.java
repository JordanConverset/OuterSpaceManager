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
 * Created by mac15 on 20/03/2017.
 */

public class FragmentGeneralDetail extends Fragment {
    private RecyclerView rvGeneralsDetail;
    private TextView tvUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_detail,container);
        rvGeneralsDetail = (RecyclerView) v.findViewById(R.id.generalsdetail);
        tvUsername = (TextView) v.findViewById(R.id.username);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvGeneralsDetail.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    public void setAttack(Attack attack) {
        rvGeneralsDetail.setAdapter(new AttacksDetailArrayAdapter(getActivity().getApplicationContext(), attack.getShips()));
    }

    public void setReport(Report report) {
        rvGeneralsDetail.setAdapter(new ReportsDetailArrayAdapter(getActivity().getApplicationContext(), report));
    }
}
