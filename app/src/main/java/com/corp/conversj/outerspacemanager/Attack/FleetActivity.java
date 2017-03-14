package com.corp.conversj.outerspacemanager.Attack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Fleet.ShipArrayAdapter;
import com.corp.conversj.outerspacemanager.Fleet.Ships;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 14/03/2017.
 */

public class FleetActivity extends Activity {
    private RecyclerView rvShips;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;
    private FleetArrayAdapter fleetAdapter;
    private Button btnAttack;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        settings = getSharedPreferences(PREFS_NAME, 0);
        btnAttack = (Button) findViewById(R.id.attack);
        rvShips = (RecyclerView) findViewById(R.id.ships);
        rvShips.setLayoutManager(new LinearLayoutManager(this));
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Ships> request = service.getFleet(settings.getString("users", new String()));
        request.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {
                fleetAdapter = new FleetArrayAdapter(getApplicationContext(), response.body().getShips());
                rvShips.setAdapter(fleetAdapter);
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

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fleetAdapter.getFleet() != null) {
                    Gson gson = new Gson();
                    Intent myIntent = new Intent(getApplicationContext(), AttackActivity.class);
                    myIntent.putExtra("fleet", gson.toJson(fleetAdapter.getFleet()));
                    startActivity(myIntent);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Vous ne pouvez pas attaquer sans flotte";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }

}
