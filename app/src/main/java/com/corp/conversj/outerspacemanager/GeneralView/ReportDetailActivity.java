package com.corp.conversj.outerspacemanager.GeneralView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;
import com.google.gson.Gson;

/**
 * Created by mac15 on 27/03/2017.
 */

public class ReportDetailActivity extends AppCompatActivity {
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        gson = new Gson();
        Report aReport = gson.fromJson(getIntent().getStringExtra("report"), Report.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentReport fragmentReport = (FragmentReport)getSupportFragmentManager().findFragmentById(R.id.fragment_reports_details);
        fragmentReport.setReport(aReport);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
