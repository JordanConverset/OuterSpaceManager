package com.corp.conversj.outerspacemanager.Buildings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.corp.conversj.outerspacemanager.Model.Building;
import com.corp.conversj.outerspacemanager.Model.Buildings;
import com.corp.conversj.outerspacemanager.R;
import com.corp.conversj.outerspacemanager.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mac15 on 07/03/2017.
 */

public class BuildingArrayAdapter extends RecyclerView.Adapter<BuildingArrayAdapter.BuildingViewHolder> {
    private final Context context;
    private final List<Building> buildings;

    public BuildingArrayAdapter(Context context, List<Building> buildings) {
        this.buildings = buildings;
        this.context = context;
    }

    @Override
    public BuildingArrayAdapter.BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);
        BuildingViewHolder viewHolder = new BuildingViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BuildingArrayAdapter.BuildingViewHolder holder, int position) {
        Building aBuilding = buildings.get(position);
        final int id = aBuilding.getBuildingId();
        final String PREFS_NAME = "OuterSpaceManager";
        holder.tvName.setText(aBuilding.getName());
        if(aBuilding.getEffect() != null) {
            holder.tvEffect.setText(aBuilding.getEffect());
        } else {
            holder.tvEffect.setText(aBuilding.getEffectAdded());
        }
        int level = aBuilding.getLevel();
        if(level == 0) {
            holder.tvGasCost.setText(String.valueOf(aBuilding.getGasCostLevel0()));
        } else {
            holder.tvGasCost.setText(String.valueOf(aBuilding.getGasCostLevel0()+aBuilding.getGasCostByLevel()*level));
        }
        if(level == 0) {
            holder.tvMineralCost.setText(String.valueOf(aBuilding.getMineralCostLevel0()));
        } else {
            holder.tvMineralCost.setText(String.valueOf(aBuilding.getMineralCostLevel0()+aBuilding.getMineralCostByLevel()*level));
        }
        if(level == 0) {
            holder.tvTimeToBuild.setText(String.valueOf(aBuilding.getTimeToBuildLevel0()));
        } else {
            holder.tvTimeToBuild.setText(String.valueOf(aBuilding.getTimeToBuildLevel0()+aBuilding.getTimeToBuildByLevel()*level));
        }
        if(level == 0) {
            holder.tvAmountEffect.setText(String.valueOf(aBuilding.getAmountOfEffectLevel0()));
        } else {
            holder.tvAmountEffect.setText(String.valueOf(aBuilding.getAmountOfEffectLevel0()+aBuilding.getAmountOfEffectByLevel()*level));
        }
        if(aBuilding.getBuilding()) {
            holder.tvFilter.setBackgroundColor(Color.GRAY);
        }
        holder.tvLevel.setText(String.valueOf(level));

        Glide
                .with(context)
                .load(aBuilding.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(holder.imBuilding);


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
        return buildings.size();
    }

    public class BuildingViewHolder extends RecyclerView.ViewHolder {
        private Button btnBuy;
        private TextView tvName;
        private TextView tvEffect;
        private TextView tvAmountEffect;
        private TextView tvGasCost;
        private TextView tvMineralCost;
        private TextView tvTimeToBuild;
        private TextView tvLevel;
        private TextView tvFilter;
        private ImageView imBuilding;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            btnBuy = (Button) itemView.findViewById(R.id.buy);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvEffect = (TextView) itemView.findViewById(R.id.effect);
            tvAmountEffect = (TextView) itemView.findViewById(R.id.amountEffect);
            tvGasCost = (TextView) itemView.findViewById(R.id.gasCost);
            tvMineralCost = (TextView) itemView.findViewById(R.id.mineralCost);
            tvTimeToBuild = (TextView) itemView.findViewById(R.id.timeToBuild);
            tvLevel = (TextView) itemView.findViewById(R.id.level);
            tvFilter = (TextView) itemView.findViewById(R.id.filter);
            imBuilding = (ImageView) itemView.findViewById(R.id.building_image);
        }
    }
}
