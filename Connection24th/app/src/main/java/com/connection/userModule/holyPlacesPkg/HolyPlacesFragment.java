package com.connection.userModule.holyPlacesPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.holyPlacesPkg.holyPlaceAdapter.HolyPlacesAdapter;

public class HolyPlacesFragment extends Fragment implements View.OnClickListener, HolyPlacesAdapter.HolyPlacesAppOnClickListener {
    private RecyclerView rvholyplaceslist;
    private HolyPlacesAdapter holyPlacesAdapter;
    private AppCompatImageView ivbackid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_holy_places, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rvholyplaceslist = view.findViewById(R.id.rvholyplaceslistId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvholyplaceslist.setLayoutManager(layoutManager);
        holyPlacesAdapter = new HolyPlacesAdapter(getActivity(), this);
        rvholyplaceslist.setAdapter(holyPlacesAdapter);
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
}
