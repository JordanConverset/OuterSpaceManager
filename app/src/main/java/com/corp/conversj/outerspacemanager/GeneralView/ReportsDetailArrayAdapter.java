package com.corp.conversj.outerspacemanager.GeneralView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corp.conversj.outerspacemanager.Model.Report;
import com.corp.conversj.outerspacemanager.R;

/**
 * Created by conversj on 21/03/2017.
 */
public class ReportsDetailArrayAdapter extends RecyclerView.Adapter<ReportsDetailArrayAdapter.ReportsDetailViewHolder> {
    private final Context context;
    private final Report report;

    public ReportsDetailArrayAdapter(Context context, Report report) {
        this.report = report;
        this.context = context;
    }

    @Override
    public ReportsDetailArrayAdapter.ReportsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reports_detail_adapter, parent, false);
        ReportsDetailArrayAdapter.ReportsDetailViewHolder viewHolder = new ReportsDetailArrayAdapter.ReportsDetailViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsDetailArrayAdapter.ReportsDetailViewHolder holder, int position) {
        //holder.tvName.setText(name);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ReportsDetailViewHolder extends RecyclerView.ViewHolder {
        //private TextView tvName;

        public ReportsDetailViewHolder(View itemView) {
            super(itemView);
            //tvName = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
