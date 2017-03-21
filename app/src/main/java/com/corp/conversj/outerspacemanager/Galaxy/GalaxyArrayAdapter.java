package com.corp.conversj.outerspacemanager.Galaxy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.Model.User;
import com.corp.conversj.outerspacemanager.R;

import java.util.List;

/**
 * Created by mac15 on 07/03/2017.
 */

public class GalaxyArrayAdapter extends RecyclerView.Adapter<GalaxyArrayAdapter.GalaxyViewHolder> {
    private final Context context;
    private final List<User> users;

    public GalaxyArrayAdapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public GalaxyArrayAdapter.GalaxyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.galaxy_adaptater, parent, false);
        GalaxyViewHolder viewHolder = new GalaxyViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(GalaxyArrayAdapter.GalaxyViewHolder holder, int position) {
        User aUser = users.get(position);
        holder.tvUsername.setText(aUser.getUsername());
        holder.tvPoints.setText(String.valueOf(aUser.getPoints()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class GalaxyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvPoints;

        public GalaxyViewHolder(View itemView) {
            super(itemView);
            tvUsername = (TextView) itemView.findViewById(R.id.username);
            tvPoints = (TextView) itemView.findViewById(R.id.points);
        }
    }
}