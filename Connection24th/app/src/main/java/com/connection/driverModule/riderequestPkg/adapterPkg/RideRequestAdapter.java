package com.connection.driverModule.riderequestPkg.adapterPkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.driverModule.riderequestPkg.requstListModlePkg.UserRequest;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RideRequestAdapter extends RecyclerView.Adapter<RideRequestAdapter.ViewHolder> {
    private Context context;
    private RideRequestOnClickListener rideRequestOnClickListener;
    private List<UserRequest> userRequestListModleList;

    public RideRequestAdapter(Context context, RideRequestOnClickListener rideRequestOnClickListener) {
        this.context = context;
        this.rideRequestOnClickListener = rideRequestOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.ride_request_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvFromLocation.setText(userRequestListModleList.get(position).getSourceLocation());
        holder.tvDestination.setText(userRequestListModleList.get(position).getDestinationLocation());
        holder.tvRiderName.setText(userRequestListModleList.get(position).getName());
        holder.tvDate.setText(userRequestListModleList.get(position).getRequestDate());
        holder.tvKmRide.setText(userRequestListModleList.get(position).getDistanceInKm());
        holder.tvTimeRide.setText(userRequestListModleList.get(position).getCreated());
    }

    public void addRequestList(List<UserRequest> userRequestListModleList) {
        this.userRequestListModleList = userRequestListModleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // return 10;
        return userRequestListModleList == null ? 0 : userRequestListModleList.size();
    }

    public interface RideRequestOnClickListener {
        void onClick(View view, int position, UserRequest userRequest);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatButton rebidnowId;
        private CircleImageView ivcontactuser;
        private AppCompatTextView tvRiderName, tvDate,
                tvFromLocation, tvDestination, tvKmRide, tvTimeRide;

        public ViewHolder(View itemView) {
            super(itemView);
            rebidnowId = itemView.findViewById(R.id.rebidnowId);
            ivcontactuser = itemView.findViewById(R.id.ivcontactuserId);
            tvRiderName = itemView.findViewById(R.id.tvRiderNameId);
            tvFromLocation = itemView.findViewById(R.id.tvFromLocationId);
            tvDestination = itemView.findViewById(R.id.tvDestinationId);
            tvDate = itemView.findViewById(R.id.tvDateId);
            tvKmRide = itemView.findViewById(R.id.tvKmRideId);
            tvTimeRide = itemView.findViewById(R.id.tvTimeRideId);
            rebidnowId.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            rideRequestOnClickListener.onClick(v, getAdapterPosition(), userRequestListModleList.get(getAdapterPosition()));
        }
    }
}