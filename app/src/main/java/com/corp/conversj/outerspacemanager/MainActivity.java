package com.corp.conversj.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Attack.AttackActivity;
import com.corp.conversj.outerspacemanager.Attack.FleetActivity;
import com.corp.conversj.outerspacemanager.Buildings.BuildingActivity;
import com.corp.conversj.outerspacemanager.Connexion.SignInActivity;
import com.corp.conversj.outerspacemanager.Fleet.ShipActivity;
import com.corp.conversj.outerspacemanager.Galaxy.GalaxyActivity;
import com.corp.conversj.outerspacemanager.Galaxy.User;
import com.corp.conversj.outerspacemanager.GeneralView.GeneralActivity;
import com.corp.conversj.outerspacemanager.Searches.SearchActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by conversj on 06/03/2017.
 */

public class MainActivity extends Activity {
    private TextView tvUsername;
    private TextView tvPoints;
    private Button btnVueGenerale;
    private Button btnBatiments;
    private Button btnFlotte;
    private Button btnRecherches;
    private Button btnChantierSpatial;
    private Button btnGalaxie;
    private Button btnDeconnexion;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Intent myIntent;
    private Double gas;
    private Double minerals;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        setContentView(R.layout.activity_main);
        tvUsername = (TextView) findViewById(R.id.username);
        tvPoints = (TextView) findViewById(R.id.points);
        btnVueGenerale = (Button) findViewById(R.id.vue_generale);
        btnBatiments = (Button) findViewById(R.id.batiments);
        btnFlotte = (Button) findViewById(R.id.flotte);
        btnRecherches = (Button) findViewById(R.id.recherches);
        btnChantierSpatial = (Button) findViewById(R.id.chantier_spatial);
        btnGalaxie = (Button) findViewById(R.id.galaxie);
        btnDeconnexion = (Button) findViewById(R.id.deconnexion);

        btnDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("users").commit();
                myIntent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(myIntent);
            }
        });

        btnBatiments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(),BuildingActivity.class);
                myIntent.putExtra("gas", gas);
                myIntent.putExtra("minerals", minerals);
                startActivity(myIntent);
            }
        });

        btnRecherches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(),SearchActivity.class);
                myIntent.putExtra("gas", gas);
                myIntent.putExtra("minerals", minerals);
                startActivity(myIntent);
            }
        });

        btnGalaxie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(),GalaxyActivity.class);
                startActivity(myIntent);
            }
        });

        btnChantierSpatial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(),ShipActivity.class);
                myIntent.putExtra("gas", gas);
                myIntent.putExtra("minerals", minerals);
                startActivity(myIntent);
            }
        });

        btnFlotte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), FleetActivity.class);
                startActivity(myIntent);
            }
        });

        btnVueGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), GeneralActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<User> request = service.getUser(settings.getString("users", new String()));

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                tvUsername.setText(response.body().getUsername());
                tvPoints.setText(String.valueOf(response.body().getPoints()));
                gas = response.body().getGas();
                minerals = response.body().getMinerals();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
