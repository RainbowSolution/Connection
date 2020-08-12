package com.connection.userModule.tiffincenterPkg.tiffinAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;

public class TiffinCenterAdapter extends RecyclerView.Adapter<TiffinCenterAdapter.ViewHolder> {
    private Context context;
    private TiffinCenterAppOnClickListener tiffinCenterAppOnClickListener;


    public TiffinCenterAdapter(Context context, TiffinCenterAppOnClickListener tiffinCenterAppOnClickListener) {
        this.context = context;
        this.tiffinCenterAppOnClickListener = tiffinCenterAppOnClickListener;
    }

    @NonNull
    @Override
    public TiffinCenterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_tiffincenters_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TiffinCenterAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public interface TiffinCenterAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
