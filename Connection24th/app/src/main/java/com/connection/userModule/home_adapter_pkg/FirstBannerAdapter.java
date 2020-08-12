package com.connection.userModule.home_adapter_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;

public class FirstBannerAdapter extends RecyclerView.Adapter<FirstBannerAdapter.ViewHolder> {
    private Context context;
    private BannerOnClickListener bannerOnClickListener;

    public FirstBannerAdapter(Context context, BannerOnClickListener bannerOnClickListener) {
        this.context = context;
        this.bannerOnClickListener = bannerOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.home_banner_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public interface BannerOnClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
        }
    }
}