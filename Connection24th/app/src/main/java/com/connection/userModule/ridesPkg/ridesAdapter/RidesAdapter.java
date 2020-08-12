package com.connection.userModule.ridesPkg.ridesAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.chatPkg.ChatActivity;
import com.connection.userModule.ridesPkg.searchRIdesPkg.SearchDriver;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RidesAdapter extends RecyclerView.Adapter<RidesAdapter.ViewHolder> {
    private Context context;
    private RidesAppOnClickListener ridesAppOnClickListener;
    private List<SearchDriver> searchDriverList;


    public RidesAdapter(Context context, RidesAppOnClickListener ridesAppOnClickListener) {
        this.context = context;
        this.ridesAppOnClickListener = ridesAppOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.searched_driver_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      /*  holder.tvVehicleName.setText(searchDriverList.get(position).getVehicleName());
        holder.tvVehicleDescripetion.setText(searchDriverList.get(position).getCurrentLocation());
        // holder.tvAvailable.setText(searchDriverList.get(position).g);

        if (searchDriverList.get(position).getVehiclePhoto().isEmpty()) {
        } else {
            Picasso.with(context).load(RetrofitClient.IMAGE_PATH + searchDriverList.get(0).getVehiclePhoto())
                    .resize(200, 200)
                    .into(holder.ivVehicleImg);
        }*/

      /*  holder.ivacceptId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void addVehicle(List<SearchDriver> searchDriverList) {
        this.searchDriverList = searchDriverList;
        notifyDataSetChanged();
    }

    public interface RidesAppOnClickListener {
        void onClick(View view, int position,SearchDriver searchDriver);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView ivacceptId, ivVehicleImg;
        private RelativeLayout rlChatNow;
        private AppCompatTextView tvVehicleName, tvVehicleDescripetion, tvAvailable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivVehicleImg = itemView.findViewById(R.id.ivVehicleImgId);

        }

        @Override
        public void onClick(View v) {
            ridesAppOnClickListener.onClick(v, getAdapterPosition(),searchDriverList.get(getAdapterPosition()));
        }
    }



}
