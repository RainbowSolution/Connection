package com.connection.userModule.indianstoresPkg.indianStoreAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;

public class IndianStoresAdapter extends RecyclerView.Adapter<IndianStoresAdapter.ViewHolder> {
    private Context context;
    private IndianStoresAppOnClickListener indianStoresAppOnClickListener;


    public IndianStoresAdapter(Context context, IndianStoresAppOnClickListener indianStoresAppOnClickListener) {
        this.context = context;
        this.indianStoresAppOnClickListener = indianStoresAppOnClickListener;
    }

    @NonNull
    @Override
    public IndianStoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_indian_store_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndianStoresAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public interface IndianStoresAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
