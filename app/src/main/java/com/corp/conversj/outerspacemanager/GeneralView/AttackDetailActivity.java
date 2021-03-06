package com.corp.conversj.outerspacemanager.GeneralView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;
import com.google.gson.Gson;

/**
 * Created by mac15 on 27/03/2017.
 */

public class AttackDetailActivity extends AppCompatActivity {
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack_detail);
        gson = new Gson();
        Attack anAttack = gson.fromJson(getIntent().getStringExtra("attack"), Attack.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentAttack fragmentAttack = (FragmentAttack)getSupportFragmentManager().findFragmentById(R.id.fragment_attacks_details);
        fragmentAttack.setAttack(anAttack);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
