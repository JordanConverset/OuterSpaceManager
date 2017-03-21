package com.corp.conversj.outerspacemanager.GeneralView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.R;
import com.google.gson.Gson;

/**
 * Created by mac15 on 20/03/2017.
 */

public class GeneralDetailActivity extends AppCompatActivity {
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_detail);
        gson = new Gson();
        Attack anAttack = gson.fromJson(getIntent().getStringExtra("attack"), Attack.class);

        FragmentGeneralDetail fragmentGeneralDetail = (FragmentGeneralDetail)getSupportFragmentManager().findFragmentById(R.id.fragment_generals_details);
        fragmentGeneralDetail.setAttack(anAttack);
    }
}
