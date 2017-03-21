package com.corp.conversj.outerspacemanager.GeneralView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.corp.conversj.outerspacemanager.Attack.onAttackClickedListener;
import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.DB.AttackDataSource;
import com.corp.conversj.outerspacemanager.Galaxy.GalaxyArrayAdapter;
import com.corp.conversj.outerspacemanager.Galaxy.Users;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 20/03/2017.
 */

public class GeneralActivity extends AppCompatActivity implements onAttackClickedListener {
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        gson = new Gson();
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
}
