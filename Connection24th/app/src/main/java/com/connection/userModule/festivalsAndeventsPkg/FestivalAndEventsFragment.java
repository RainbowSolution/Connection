package com.connection.userModule.festivalsAndeventsPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.festivalsAndeventsPkg.festivalAdapter.FestivalAndEventsAdapter;

public class FestivalAndEventsFragment extends Fragment
        implements View.OnClickListener,
        FestivalAndEventsAdapter.FestivalAndEventsAppOnClickListener {
    private RecyclerView rvfestivallist;
    private FestivalAndEventsAdapter festivalAndEventsAdapter;
    private AppCompatImageView ivbackid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_festival_and_events, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rvfestivallist = view.findViewById(R.id.rvfestivallistId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvfestivallist.setLayoutManager(layoutManager);
        festivalAndEventsAdapter = new FestivalAndEventsAdapter(getActivity(), this);
        rvfestivallist.setAdapter(festivalAndEventsAdapter);
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
