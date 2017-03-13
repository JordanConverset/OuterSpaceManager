package com.corp.conversj.outerspacemanager.Connexion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.MainActivity;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.corp.conversj.outerspacemanager.Galaxy.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends Activity {

    private Button addBtn;
    private Button loginBtn;
    private EditText username;
    private EditText password;
    private User user;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Intent myIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        if(!settings.getString("users",new String()).isEmpty()) {
            myIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(myIntent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.input_identifiant);
        password = (EditText) findViewById(R.id.input_mdp);
        addBtn = (Button) findViewById(R.id.btn_add);
        loginBtn = (Button) findViewById(R.id.btn_login);

        addBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals("")  && !password.getText().toString().equals("")) {
                    user = new User(username.getText().toString(), password.getText().toString());
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "The password or the username are missing";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<User> request = service.createUserAccount(user);

                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null) {
                            editor.putString("users", response.body().getToken());
                            editor.commit();
                            myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Username already exist.";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
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
        });
        
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
