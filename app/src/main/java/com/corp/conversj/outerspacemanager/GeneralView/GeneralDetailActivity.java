package com.corp.conversj.outerspacemanager.GeneralView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.Model.Report;
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
        Report aReport = gson.fromJson(getIntent().getStringExtra("report"), Report.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentGeneralDetail fragmentGeneralDetail = (FragmentGeneralDetail)getSupportFragmentManager().findFragmentById(R.id.fragment_generals_details);
        fragmentGeneralDetail.setAttack(anAttack);
        fragmentGeneralDetail.setReport(aReport);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
