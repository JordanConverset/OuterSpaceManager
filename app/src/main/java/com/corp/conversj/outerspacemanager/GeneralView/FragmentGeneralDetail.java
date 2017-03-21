package com.corp.conversj.outerspacemanager.GeneralView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.R;

/**
 * Created by mac15 on 20/03/2017.
 */

public class FragmentGeneralDetail extends Fragment {
    private TextView tvUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_detail,container);
        tvUsername = (TextView) v.findViewById(R.id.username);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setAttack(Attack attack) {
        tvUsername.setText(attack.getUsername());
    }
}
