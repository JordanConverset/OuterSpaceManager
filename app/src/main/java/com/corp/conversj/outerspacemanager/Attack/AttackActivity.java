package com.corp.conversj.outerspacemanager.Attack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Fleet.Ship;
import com.corp.conversj.outerspacemanager.Fleet.Ships;
import com.corp.conversj.outerspacemanager.Galaxy.GalaxyArrayAdapter;
import com.corp.conversj.outerspacemanager.Galaxy.Users;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 14/03/2017.
 */

public class AttackActivity extends Activity{
    private RecyclerView rvUsers;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;
    private Gson gson;
    private Ships fleet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);
        settings = getSharedPreferences(PREFS_NAME, 0);
        rvUsers = (RecyclerView) findViewById(R.id.users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        fleet = gson.fromJson(intent.getStringExtra("fleet"), Ships.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Users> request = service.getUsers(settings.getString("users", new String()));
        request.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                rvUsers.setAdapter(new AttackArrayAdapter(AttackActivity.this, response.body().getUsers(), fleet, settings.getString("users", new String())));
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}
