package com.connection.userModule.accommodationPkg.accomodationAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.accommodationPkg.accomodationModle.Datum;

import java.util.List;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.ViewHolder> {
    private Context context;
    private AccommodationAppOnClickListener accommodationAppOnClickListener;
    private List<Datum> getlist;

    public AccommodationAdapter(Context context, AccommodationAppOnClickListener accommodationAppOnClickListener) {
        this.context = context;
        this.accommodationAppOnClickListener = accommodationAppOnClickListener;
    }


    @NonNull
    @Override
    public AccommodationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_accommodation_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccommodationAdapter.ViewHolder holder, int position) {
        // Picasso.get().load(RetrofitClient.CATEGORY_URL + getlist.get(position).getCatImages()).into(holder.ivaccommodationimgId);
        holder.tvhotelnameId.setText(getlist.get(position).getProductName());
    }

    public void getaccommodationList(List<Datum> getlist) {
        this.getlist = getlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getlist == null ? 0 : getlist.size();
    }

    public interface AccommodationAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView ivaccommodationimgId;
        private AppCompatTextView tvhotelnameId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivaccommodationimgId = itemView.findViewById(R.id.ivaccommodationimgId);
            tvhotelnameId = itemView.findViewById(R.id.tvhotelnameId);
        }
    }
}
