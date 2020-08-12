package com.connection.userModule.ridesPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.userModule.ridesPkg.ridesAdapter.RidesAdapter;
import com.connection.userModule.ridesPkg.searchRIdesPkg.SearchDriver;
import com.connection.utility.CheckNetwork;

import java.util.List;

public class PassangerFragment  extends Fragment implements View.OnClickListener, RidesAdapter.RidesAppOnClickListener {
    private RecyclerView rvcarslist;
    private RidesAdapter ridesAdapter;
    private AppCompatTextView tvAvailabltVehicleCount;
    private String currentLocation, dropLocation, current_location_lat, current_location_long;
    private ApiServices apiServices;
    private String userId;
    private List<SearchDriver> searchedDriverVehicleModleList;
    private AppCompatTextView tvRecordNotFound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_passengers, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        init(view);
        if (CheckNetwork.isNetAvailable(getActivity())) {
            //  getSearchedVehicle();
        } else {
            Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    /*current_location_lat, current_location_long, currentLocation, dropLocation*/


    private void init(View view) {
        tvRecordNotFound = view.findViewById(R.id.tvRecordNotFoundId);
        rvcarslist = view.findViewById(R.id.rvcarslistId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvcarslist.setLayoutManager(layoutManager);
        ridesAdapter = new RidesAdapter(getActivity(), this);
        rvcarslist.setAdapter(ridesAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(View view, int position, SearchDriver searchDriver) {
        switch (view.getId()) {
            case R.id.ivacceptId:
                if (CheckNetwork.isNetAvailable(getActivity())) {
                } else {
                    Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}