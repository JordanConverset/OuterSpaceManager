package com.corp.conversj.outerspacemanager.GeneralView;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.corp.conversj.outerspacemanager.Attack.onAttackClickedListener;
import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.DB.AttackDataSource;
import com.corp.conversj.outerspacemanager.Galaxy.GalaxyArrayAdapter;
import com.corp.conversj.outerspacemanager.Galaxy.Users;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by mac15 on 20/03/2017.
 */
public class FragmentGeneral extends Fragment {

    private RecyclerView rvGenerals;
    private ToggleButton btnToggle;
    private Retrofit retrofit;
    private Service service;
    private AttackDataSource db;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general,container);
        rvGenerals = (RecyclerView) v.findViewById(R.id.generals);
        btnToggle = (ToggleButton) v.findViewById(R.id.btn_toggle);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvGenerals.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        btnToggle.setTextOn("Voir les rapports");
        btnToggle.setTextOff("Voir les attaques en cours");
        btnToggle.setChecked(false);
        settings = getActivity().getSharedPreferences(PREFS_NAME, 0);

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnToggle.isChecked()){
                    getAttacks();
                }else{
                    getReports();
                }
            }
        });

    }

    public void getReports() {
        Call<Users> request = service.getUsers(settings.getString("users", new String()));
        request.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                rvGenerals.setAdapter(new GalaxyArrayAdapter(getActivity().getApplicationContext(), response.body().getUsers()));
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void getAttacks() {
        db = new AttackDataSource(getActivity().getApplicationContext());
        db.open();
        AttacksArrayAdapter adapter = new AttacksArrayAdapter(getActivity().getApplicationContext(), db.getAllAttacks());
        adapter.setListener((GeneralActivity)getActivity());
        rvGenerals.setAdapter(adapter);
        db.close();
    }
}
