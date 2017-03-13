package com.corp.conversj.outerspacemanager.Searches;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.conversj.outerspacemanager.Buildings.Buildings;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac15 on 07/03/2017.
 */

public class SearchArrayAdapter extends RecyclerView.Adapter<SearchArrayAdapter.SearchViewHolder> {
    private final Context context;
    private final List<Search> searches;

    public SearchArrayAdapter(Context context, List<Search> searches) {
        this.searches = searches;
        this.context = context;
    }

    @Override
    public SearchArrayAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_adapter, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchArrayAdapter.SearchViewHolder holder, int position) {
        Search aSearch = searches.get(position);
        final int id = aSearch.getBuildingId();
        final String PREFS_NAME = "OuterSpaceManager";
        holder.tvName.setText(aSearch.getName());
        if(aSearch.getEffect() != null) {
            holder.tvEffect.setText(aSearch.getEffect());
        } else {
            holder.tvEffect.setText(aSearch.getEffectAdded());
        }
        int level = aSearch.getLevel();
        if(level == 0) {
            holder.tvGasCost.setText(aSearch.getGasCostLevel0());
        } else {
            holder.tvGasCost.setText(String.valueOf(aSearch.getGasCostLevel0()+aSearch.getGasCostByLevel()*level));
        }
        if(level == 0) {
            holder.tvMineralCost.setText(aSearch.getMineralCostLevel0());
        } else {
            holder.tvMineralCost.setText(String.valueOf(aSearch.getMineralCostLevel0()+aSearch.getMineralCostByLevel()*level));
        }
        if(level == 0) {
            holder.tvTimeToBuild.setText(aSearch.getTimeToBuildLevel0());
        } else {
            holder.tvTimeToBuild.setText(String.valueOf(aSearch.getTimeToBuildLevel0()+aSearch.getTimeToBuildByLevel()*level));
        }
        if(level == 0) {
            holder.tvAmountEffect.setText(aSearch.getAmountOfEffectLevel0());
        } else {
            holder.tvAmountEffect.setText(String.valueOf(aSearch.getAmountOfEffectLevel0()+aSearch.getAmoutOfEffectByLevel()*level));
        }
        if(aSearch.getBuilding()) {
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        holder.tvLevel.setText(String.valueOf(level));

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<Buildings> request = service.createBuilding(settings.getString("users", new String()), String.valueOf(id));

                request.enqueue(new Callback<Buildings>() {
                    @Override
                    public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                        if(response.code() == 200) {
                            CharSequence text = "En cours de construction";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            CharSequence text = "Pas assez de ressources";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Buildings> call, Throwable t) {
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
        return searches.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private Button btnBuy;
        private TextView tvName;
        private TextView tvEffect;
        private TextView tvAmountEffect;
        private TextView tvGasCost;
        private TextView tvMineralCost;
        private TextView tvTimeToBuild;
        private TextView tvLevel;

        public SearchViewHolder(View itemView) {
            super(itemView);
            btnBuy = (Button) itemView.findViewById(R.id.buy);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvEffect = (TextView) itemView.findViewById(R.id.effect);
            tvAmountEffect = (TextView) itemView.findViewById(R.id.amountEffect);
            tvGasCost = (TextView) itemView.findViewById(R.id.gasCost);
            tvMineralCost = (TextView) itemView.findViewById(R.id.mineralCost);
            tvTimeToBuild = (TextView) itemView.findViewById(R.id.timeToBuild);
            tvLevel = (TextView) itemView.findViewById(R.id.level);
        }
    }
}
