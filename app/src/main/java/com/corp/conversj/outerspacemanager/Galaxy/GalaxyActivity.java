package com.corp.conversj.outerspacemanager.Galaxy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Model.Users;
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

public class GalaxyActivity extends AppCompatActivity {
    private RecyclerView rvUsers;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;
    private int from = 0;
    private int tmpFrom = from;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy);
        settings = getSharedPreferences(PREFS_NAME, 0);
        rvUsers = (RecyclerView) findViewById(R.id.users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Service service = retrofit.create(Service.class);
        Call<Users> request = service.getUsers(settings.getString("users", new String()), String.valueOf(from), "20");
        request.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                rvUsers.setAdapter(new GalaxyArrayAdapter(getApplicationContext(), response.body().getUsers()));
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

        rvUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!recyclerView.canScrollVertically(1)) {
                    int fromTmp = from + 20;
                    if(fromTmp != tmpFrom) {
                        tmpFrom = fromTmp;
                        Call<Users> request = service.getUsers(settings.getString("users", new String()), String.valueOf(from), "20");
                        request.enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(Call<Users> call, Response<Users> response) {
                                rvUsers.setAdapter(new GalaxyArrayAdapter(getApplicationContext(), response.body().getUsers()));
                                from = tmpFrom;
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

                if(!recyclerView.canScrollVertically(-1)) {
                    if(from != 0) {
                        int fromTmp = from - 20;
                        if (fromTmp != tmpFrom) {
                            tmpFrom = fromTmp;
                            Call<Users> request = service.getUsers(settings.getString("users", new String()), String.valueOf(from), "20");
                            request.enqueue(new Callback<Users>() {
                                @Override
                                public void onResponse(Call<Users> call, Response<Users> response) {
                                    rvUsers.setAdapter(new GalaxyArrayAdapter(getApplicationContext(), response.body().getUsers()));
                                    from = tmpFrom;
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
                }
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
