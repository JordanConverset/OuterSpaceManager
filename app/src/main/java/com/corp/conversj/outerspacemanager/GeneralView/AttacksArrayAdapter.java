package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.corp.conversj.outerspacemanager.Attack.onAttackClickedListener;
import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.DB.AttackDataSource;
import com.corp.conversj.outerspacemanager.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mac15 on 20/03/2017.
 */

public class AttacksArrayAdapter extends RecyclerView.Adapter<AttacksArrayAdapter.AttacksViewHolder>{
    private final Context context;
    private final List<Attack> attacks;
    private onAttackClickedListener listener;

    public AttacksArrayAdapter(Context context, List<Attack> attacks) {
        this.attacks = attacks;
        this.context = context;
    }

    @Override
    public AttacksArrayAdapter.AttacksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.attacks_adapter, parent, false);
        AttacksArrayAdapter.AttacksViewHolder viewHolder = new AttacksArrayAdapter.AttacksViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AttacksArrayAdapter.AttacksViewHolder holder, int position) {
        final Attack anAttack = attacks.get(position);
        if(System.currentTimeMillis() > anAttack.getAttackTime()){
            holder.tvTime.setText("Attaque terminée");
            holder.tvUsername.setText(anAttack.getUsername());
            AttackDataSource db = new AttackDataSource(context);
            db.open();
            db.deleteAttack(anAttack);
            db.close();
        } else {
            Date date = new Date(anAttack.getAttackTime());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateFormatted = formatter.format(date);
            holder.tvTime.setText(dateFormatted);
            holder.tvUsername.setText(anAttack.getUsername());
            holder.progressBar.setMax(100);
            holder.progressBar.setProgress(anAttack.getProgress());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAttackClicked(anAttack);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return attacks.size();
    }

    public void setListener(onAttackClickedListener listener) {
        this.listener = listener;
    }

    public class AttacksViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvUsername;
        public ProgressBar progressBar;

        public AttacksViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvUsername = (TextView) itemView.findViewById(R.id.username);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
