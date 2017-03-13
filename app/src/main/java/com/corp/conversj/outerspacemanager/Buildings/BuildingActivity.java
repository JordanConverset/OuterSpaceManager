package com.corp.conversj.outerspacemanager.Buildings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 07/03/2017.
 */

public class BuildingActivity extends Activity {
    private RecyclerView rvBuildings;
    private TextView tvGas;
    private TextView tvMinerals;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        settings = getSharedPreferences(PREFS_NAME, 0);
        rvBuildings = (RecyclerView) findViewById(R.id.buildings);
        rvBuildings.setLayoutManager(new LinearLayoutManager(this));
        tvGas = (TextView) findViewById(R.id.gas);
        tvMinerals = (TextView) findViewById(R.id.minerals);
        Intent intent = getIntent();

        tvGas.setText(String.valueOf((int)intent.getDoubleExtra("gas",0)));
        tvMinerals.setText(String.valueOf((int)intent.getDoubleExtra("minerals",0)));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Buildings> request = service.getBuildings(settings.getString("users", new String()));
        request.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                rvBuildings.setAdapter(new BuildingArrayAdapter(getApplicationContext(), response.body().getBuildings()));
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}
