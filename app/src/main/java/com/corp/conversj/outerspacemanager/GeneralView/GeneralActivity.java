package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Intent;
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

public class GeneralActivity extends AppCompatActivity implements OnGeneralClickedListener {
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        gson = new Gson();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onAttackClicked(Attack attack) {
        FragmentGeneral fragmentGeneral = (FragmentGeneral) getSupportFragmentManager().findFragmentById(R.id.fragment_generals);
        FragmentGeneralDetail fragmentGeneralDetail = (FragmentGeneralDetail) getSupportFragmentManager().findFragmentById(R.id.fragment_generals_details);
        if (fragmentGeneralDetail == null || !fragmentGeneralDetail.isInLayout()) {
            Intent myIntent = new Intent(getApplicationContext(), GeneralDetailActivity.class);
            myIntent.putExtra("attack", gson.toJson(attack));
            startActivity(myIntent);
        } else {
            fragmentGeneralDetail.setAttack(attack);
        }
    }

    @Override
    public void onReportClicked(Report report) {
        FragmentGeneral fragmentGeneral = (FragmentGeneral) getSupportFragmentManager().findFragmentById(R.id.fragment_generals);
        FragmentGeneralDetail fragmentGeneralDetail = (FragmentGeneralDetail) getSupportFragmentManager().findFragmentById(R.id.fragment_generals_details);
        if (fragmentGeneralDetail == null || !fragmentGeneralDetail.isInLayout()) {
            Intent myIntent = new Intent(getApplicationContext(), GeneralDetailActivity.class);
            myIntent.putExtra("report", gson.toJson(report));
            startActivity(myIntent);
        } else {
            fragmentGeneralDetail.setReport(report);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
