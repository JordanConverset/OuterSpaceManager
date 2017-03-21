package com.corp.conversj.outerspacemanager.Attack;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Model.AttackResponse;
import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.DB.AttackDataSource;
import com.corp.conversj.outerspacemanager.Model.Ships;
import com.corp.conversj.outerspacemanager.Model.User;
import com.corp.conversj.outerspacemanager.MainActivity;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 14/03/2017.
 */

public class AttackArrayAdapter extends RecyclerView.Adapter<AttackArrayAdapter.AttackViewHolder>{
    private final Context context;
    private final List<User> users;
    private final Ships ships;
    private Retrofit retrofit;
    public static final String PREFS_NAME = "OuterSpaceManager";
    private final String token;

    public AttackArrayAdapter(Context context, List<User> users, Ships ships, String token) {
        this.users = users;
        this.ships = ships;
        this.context = context;
        this.token = token;
    }

    @Override
    public AttackArrayAdapter.AttackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.attack_adapter, parent, false);
        AttackArrayAdapter.AttackViewHolder viewHolder = new AttackArrayAdapter.AttackViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AttackArrayAdapter.AttackViewHolder holder, int position) {
        final User aUser = users.get(position);
        holder.tvUsername.setText(aUser.getUsername());
        holder.tvPoints.setText(String.valueOf(aUser.getPoints()));

        holder.llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<AttackResponse> request = service.attack(token, aUser.getUsername(), ships);
                request.enqueue(new Callback<AttackResponse>() {
                    @Override
                    public void onResponse(Call<AttackResponse> call, Response<AttackResponse> response) {
                        if(response.code() == 200) {
                            Attack anAttack = new Attack();
                            anAttack.setShips(ships);
                            anAttack.setUsername(aUser.getUsername());
                            anAttack.setAttackTime(response.body().getAttackTime());
                            AttackDataSource attackDataSource = new AttackDataSource(context);
                            attackDataSource.open();
                            attackDataSource.createAttack(anAttack);
                            attackDataSource.close();
                            CharSequence text = aUser.getUsername()+" va souffrir, tout est une question de temps !";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            CharSequence text = "Arghhhhhhh ! L'attaque a échoué !";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        Intent myItent = new Intent(context, MainActivity.class);
                        context.startActivity(myItent);
                    }

                    @Override
                    public void onFailure(Call<AttackResponse> call, Throwable t) {
                        CharSequence text = "Error";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class AttackViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvPoints;
        private LinearLayout llUser;

        public AttackViewHolder(View itemView) {
            super(itemView);
            tvUsername = (TextView) itemView.findViewById(R.id.username);
            tvPoints = (TextView) itemView.findViewById(R.id.points);
            llUser = (LinearLayout) itemView.findViewById(R.id.user);
        }
    }
}
