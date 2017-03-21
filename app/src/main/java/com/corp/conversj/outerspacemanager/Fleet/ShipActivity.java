package com.corp.conversj.outerspacemanager.Fleet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Model.Ships;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 13/03/2017.
 */

public class ShipActivity extends AppCompatActivity{
    private RecyclerView rvShips;
    private TextView tvGas;
    private TextView tvMinerals;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);
        settings = getSharedPreferences(PREFS_NAME, 0);
        rvShips = (RecyclerView) findViewById(R.id.ships);
        rvShips.setLayoutManager(new LinearLayoutManager(this));
        tvGas = (TextView) findViewById(R.id.gas);
        tvMinerals = (TextView) findViewById(R.id.minerals);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvGas.setText(String.valueOf((int)intent.getDoubleExtra("gas",0)));
        tvMinerals.setText(String.valueOf((int)intent.getDoubleExtra("minerals",0)));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Ships> request = service.getShips(settings.getString("users", new String()));
        request.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {
                rvShips.setAdapter(new ShipArrayAdapter(getApplicationContext(), response.body().getShips()));
            }

            @Override
            public void onFailure(Call<Ships> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
