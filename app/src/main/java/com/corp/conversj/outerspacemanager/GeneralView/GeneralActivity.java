package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;
import com.google.gson.Gson;

/**
 * Created by mac15 on 20/03/2017.
 */

public class GeneralActivity extends AppCompatActivity implements OnGeneralClickedListener {
    Gson gson;
    private FrameLayout viewById;

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
        viewById = (FrameLayout) findViewById(R.id.flFragmentContainer);
        if (viewById == null) {
            Intent myIntent = new Intent(getApplicationContext(), AttackDetailActivity.class);
            myIntent.putExtra("attack", gson.toJson(attack));
            startActivity(myIntent);
        } else {
            FragmentAttack attackFragment = new FragmentAttack();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragmentContainer, attackFragment);
            transaction.commitNow();
            attackFragment.setAttack(attack);
        }
    }

    @Override
    public void onReportClicked(Report report) {
        viewById = (FrameLayout) findViewById(R.id.flFragmentContainer);
        if (viewById == null) {
            Intent myIntent = new Intent(getApplicationContext(), ReportDetailActivity.class);
            myIntent.putExtra("report", gson.toJson(report));
            startActivity(myIntent);
        } else {
            FragmentReport reportFragment = new FragmentReport();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragmentContainer, reportFragment);
            transaction.commitNow();
            reportFragment.setReport(report);
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
