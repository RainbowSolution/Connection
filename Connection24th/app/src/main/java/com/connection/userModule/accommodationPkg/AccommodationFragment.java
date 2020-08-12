package com.connection.userModule.accommodationPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.userModule.accommodationPkg.accomodationAdapter.AccommodationAdapter;
import com.connection.userModule.accommodationPkg.accomodationModle.Datum;
import com.connection.userModule.accommodationPkg.accomodationModle.GetAccommodationModel;
import com.connection.utility.CustomProgressbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccommodationFragment extends Fragment implements View.OnClickListener, AccommodationAdapter.AccommodationAppOnClickListener {

    private RecyclerView rvaccommodationlist;
    private AccommodationAdapter accommodationAdapter;
    private ApiServices apiServices;
    private List<Datum> getaccommodationlist;
    private RelativeLayout renotificaionId;
    private AppCompatImageView ivbackid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_accommodation, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        init(view);
        return view;
    }

    private void init(View view) {
        rvaccommodationlist = view.findViewById(R.id.rvvideoslistId);
        renotificaionId = view.findViewById(R.id.renotificaionId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvaccommodationlist.setLayoutManager(layoutManager);
        accommodationAdapter = new AccommodationAdapter(getActivity(), this);
        rvaccommodationlist.setAdapter(accommodationAdapter);
        getaccommodationList();
        ivbackid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackid:
                getActivity().onBackPressed();
                break;
        }
    }

    private void getaccommodationList() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.getaccommodationList("1").enqueue(new Callback<GetAccommodationModel>() {
            @Override
            public void onResponse(Call<GetAccommodationModel> call, Response<GetAccommodationModel> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    GetAccommodationModel getBookingModle = response.body();
                    if (getBookingModle.getStatus().equals(true)) {
                        getaccommodationlist = getBookingModle.getData();
                        accommodationAdapter.getaccommodationList(getaccommodationlist);
                    } else {
                        renotificaionId.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<GetAccommodationModel> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }
}
